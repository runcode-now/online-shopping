/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.team3.onlineshopping.controllerSalesman;

import com.team3.onlineshopping.dal.OrderDAO;
import com.team3.onlineshopping.model.ExcelStatistic;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Admin
 */
public class SaleExportExcelServlet extends HttpServlet {
   
    OrderDAO order_dao = new OrderDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/vnd.ms-excel");
        // Tên file cần encoding trước khi đặt vào tiêu đề
        String fileName = "Chi tiết đơn hàng.xlsx";
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
            fileName = fileName.replaceAll("\\+", "%20"); // Thay thế dấu "+" bằng "%20"
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Chi tiết đơn hàng theo ngày");
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

            // Đặt màu nền cho header (màu trắng)
            headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Can giua
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
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    }


}
