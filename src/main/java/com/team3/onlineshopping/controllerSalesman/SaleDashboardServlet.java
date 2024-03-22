/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.team3.onlineshopping.controllerSalesman;

import com.team3.onlineshopping.dal.OrderDAO;
import com.team3.onlineshopping.model.AccountStatistic;
import com.team3.onlineshopping.model.ExcelStatistic;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Admin
 */
public class SaleDashboardServlet extends HttpServlet {

    OrderDAO order_dao = new OrderDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<AccountStatistic> deliveredStatistic = new ArrayList<>();
        List<AccountStatistic> deliveringStatistic = new ArrayList<>();
        List<AccountStatistic> cancelledStatistic = new ArrayList<>();
        List<AccountStatistic> pendingStatistic = new ArrayList<>();
        List<AccountStatistic> top5Statistic = new ArrayList<>();

        long maxValue = 0;

        String view = request.getParameter("view");
        String select = request.getParameter("select");
        view = (view == null) ? "day" : view;
        select = (select == null) ? "order" : select;

        if (select.equals("order")) {
            if (view.equals("day")) {
                deliveredStatistic = order_dao.getStatisticOrderBy7days("delivered");
                deliveringStatistic = order_dao.getStatisticOrderBy7days("delivering");
                cancelledStatistic = order_dao.getStatisticOrderBy7days("cancelled");
                pendingStatistic = order_dao.getStatisticOrderBy7days("pending");

                top5Statistic = order_dao.getStatisticTop5("delivered", "day");
            } else { // month
                deliveredStatistic = order_dao.getStatisticOrderByMonths("delivered");
                deliveringStatistic = order_dao.getStatisticOrderByMonths("delivering");
                cancelledStatistic = order_dao.getStatisticOrderByMonths("cancelled");
                pendingStatistic = order_dao.getStatisticOrderByMonths("pending");

                top5Statistic = order_dao.getStatisticTop5("delivered", "month");
            }
        } else { // price
            if (view.equals("day")) {
                deliveredStatistic = order_dao.getStatisticPriceBy7days("delivered");
                deliveringStatistic = order_dao.getStatisticPriceBy7days("delivering");
                cancelledStatistic = order_dao.getStatisticPriceBy7days("cancelled");
                pendingStatistic = order_dao.getStatisticPriceBy7days("pending");

                top5Statistic = order_dao.getStatisticTop5("delivered", "day");
            } else { // month
                deliveredStatistic = order_dao.getStatisticPriceByMonths("delivered");
                deliveringStatistic = order_dao.getStatisticPriceByMonths("delivering");
                cancelledStatistic = order_dao.getStatisticPriceByMonths("cancelled");
                pendingStatistic = order_dao.getStatisticPriceByMonths("pending");

                top5Statistic = order_dao.getStatisticTop5("delivered", "month");
            }
        }

        maxValue = findMaxValue(findMaxList(deliveredStatistic), findMaxList(deliveringStatistic),
                findMaxList(cancelledStatistic), findMaxList(pendingStatistic));

        maxValue = roundNumber(maxValue);
        if (maxValue < 10) {
            maxValue *= 10;
        }

        request.setAttribute("step", maxValue / 10);
        request.setAttribute("maxValue", maxValue);

        request.setAttribute("deliveredList", deliveredStatistic);
        request.setAttribute("deliveringList", deliveringStatistic);
        request.setAttribute("cancelledList", cancelledStatistic);
        request.setAttribute("pendingList", pendingStatistic);

        request.setAttribute("top5Statistic", top5Statistic);

        request.setAttribute("view", view);
        request.setAttribute("select", select);
        request.getRequestDispatcher("s_dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=userList.xlsx");

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("list");
        List<ExcelStatistic> list = order_dao.getStatisticExcel();

        // ================== CREATE EXCEL  ====================================
        // 1. HEADER
        int rowNo = 0;
        Row row = sheet.createRow(rowNo++);
        int cellnum = 0;

        Cell cell = row.createCell(cellnum++);
        cell.setCellValue("Ngày");

        cell = row.createCell(cellnum++);
        cell.setCellValue("Tổng doanh số");

        cell = row.createCell(cellnum++);
        cell.setCellValue("Tổng số đơn hàng");

        cell = row.createCell(cellnum++);
        cell.setCellValue("Doanh số trên mỗi đơn hàng");

        cell = row.createCell(cellnum++);
        cell.setCellValue("Trạng thái");

        // Bôi đậm header
        CellStyle headerStyle = wb.createCellStyle();
        Font headerFont = wb.createFont();
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);

        for (int i = 0; i < cellnum; i++) {
            row.getCell(i).setCellStyle(headerStyle);
        }

        // 2. DETAILS
        for (ExcelStatistic orders : list) {
            cellnum = 0;
            row = sheet.createRow(rowNo++);
            cell = row.createCell(cellnum++);
            cell.setCellValue(orders.getOrderDate());

            cell = row.createCell(cellnum++);
            cell.setCellValue(orders.getTotalPrice());

            cell = row.createCell(cellnum++);
            cell.setCellValue(orders.getOrderCount());

            cell = row.createCell(cellnum++);
            cell.setCellValue(orders.getAveragePrice());

            cell = row.createCell(cellnum++);
            cell.setCellValue(orders.getOrderStatus());
        }

        // Tự động điều chỉnh kích thước cột để vừa với nội dung
        for (int i = 0; i < cellnum; i++) {
            sheet.autoSizeColumn(i);
        }

        // =================== End create excel ================================
        wb.write(response.getOutputStream());
        wb.close();

        response.sendRedirect("s_dashboard.jsp");

    }

    private long findMaxList(List<AccountStatistic> list) {
        long max = list.get(0).getTotalAccount();
        for (AccountStatistic acc : list) {
            if (max < acc.getTotalAccount()) {
                max = acc.getTotalAccount();
            }
        }
        return max;
    }

    private long findMaxValue(long num1, long num2, long num3, long num4) {
        long max1 = Math.max(num1, num2);
        long max2 = Math.max(num3, num4);

        return Math.max(max1, max2);
    }

    private static long roundNumber(long value) {
        long temp = value;
        int count = 0;
        while (temp / 10 != 0) {
            count++;
            temp = temp / 10;
        }
        long round = (long) Math.pow(10, count);

        // Step 1: Tang gia tri max
        value = (value / 10) + value;

        // Step 2: Lam tron maxValue
        long newValue = ((long) Math.ceil((double) value / round)) * round;

        return newValue;
    }

}
