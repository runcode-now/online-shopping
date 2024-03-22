package com.team3.onlineshopping.controllerAdmin;

import com.team3.onlineshopping.dal.AccountDAO;
import com.team3.onlineshopping.model.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author Admin
 */
public class AdminUserListServlet extends HttpServlet {
    
    AccountDAO acc_dao = new AccountDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Account> accList = acc_dao.splitPageAccount(10, 1, "", "", "", "", 0);

        int numberPage = (int) Math.ceil((double) acc_dao.getTotalAccount() / 10);

        request.setAttribute("numberPage", numberPage);
        request.setAttribute("page", 1);
        request.setAttribute("quantity", 10);
        request.setAttribute("accList", accList);
        request.getRequestDispatcher("a_userlist.jsp").forward(request, response);
//        request.getRequestDispatcher("test.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String option = request.getParameter("option");
        String textSearch = request.getParameter("textSearch");
        String dateBegin = request.getParameter("dateBegin");
        String dateEnd = request.getParameter("dateEnd");
        String statusFilter = request.getParameter("statusFilter");
        String role = request.getParameter("roleFilter");
        String pageNum = request.getParameter("page");
        String quantity = request.getParameter("quantityAccount");

        option = (option == null) ? "" : option;
        textSearch = (textSearch == null) ? "" : textSearch;
        dateBegin = (dateBegin == null) ? "" : dateBegin;
        dateEnd = (dateEnd == null) ? "" : dateEnd;
        statusFilter = (statusFilter == null) ? "" : statusFilter;
        role = (role == null) ? "" : role;
        pageNum = (pageNum == null) ? "" : pageNum;
        quantity = (quantity == null) ? "" : quantity;

        int quantityAccount = Integer.parseInt(quantity);  // give the quantity account
        int roleFilter = Integer.parseInt(role);
        int totalAccount = acc_dao.getTotalAccountByCondition(textSearch, dateBegin, dateEnd, statusFilter, roleFilter);
        int numberPage = 1;

        int page = 1;
        // user don't choose page
        if (!pageNum.isEmpty()) {
            page = Integer.parseInt(pageNum);
        }

        // give the quantity of page
        if (quantityAccount != 0) {
            numberPage = (int) Math.ceil((double) totalAccount / quantityAccount);
        }
        List<Account> accList = acc_dao.splitPageAccount(quantityAccount, page, textSearch, dateBegin, dateEnd, statusFilter, roleFilter);

        // when user choose page
        if (option.equals("split")) {
            int number = quantityAccount * (page - 1) + 1;

            out.println("<table class=\"table-user\">");
            out.println("<tr class=\"table-user__header\">");
            out.println("    <th style=\"width: 5%;\">STT</th>");
            out.println("    <th style=\"width: 15%;\">Tên</th>");
            out.println("    <th style=\"width: 20%;\">Email</th>");
            out.println("    <th style=\"width: 10%;\">Số điện thoại</th>");
            out.println("    <th style=\"width: 18%;\">Trạng thái</th>");
            out.println("    <th style=\"width: 10%;\">Ngày tạo</th>");
            out.println("    <th style=\"width: 15%;\">Vai trò</th>");
            out.println("    <th style=\"width: 7%;\">Chỉnh sửa</th>");
            out.println("</tr>");

            for (Account acc : accList) {
                createAccountHtml(acc, number, request, response);
                number++;
            }
            out.println("</table>");

            out.println("<div class=\"page\">");
            for (int i = 1; i <= numberPage; i++) {
                if (page == i) {
                    out.println("<a href=\"#\" class=\"page__number--chosen\" onclick=\"movePage(" + i + ")\"><p>" + i + "</p></a>");
                } else {
                    out.println("<a href=\"#\" class=\"page__number\" onclick=\"movePage(" + i + ")\"><p>" + i + "</p></a>");
                }
            }
            out.println("</div>");
            out.close();
        }

        // user just use filter
        if (option.isEmpty()) {
            request.setAttribute("numberPage", numberPage);
            request.setAttribute("page", page);
            request.setAttribute("quantity", quantityAccount);
            request.setAttribute("accList", accList);
            request.getRequestDispatcher("a_userlist.jsp").forward(request, response);
        } else {
            response.sendRedirect("a_userlist.jsp");
        }
    }

    public void createAccountHtml(Account acc, int number, HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<tr class=\"table-user__body\" id=\"row_" + acc.getAccId() + "\">");
        out.println("    <td>" + number + "</td>");
        out.println("    <td>" + acc.getAccName() + "</td>");
        out.println("    <td>" + acc.getAccEmail() + "</td>");
        out.println("    <td>" + acc.getAccPhone() + "</td>");
        out.println("    <td>");
        out.println("        <select name=\"status\" class=\"form-control\" onchange=\"updateInfo(" + acc.getAccId() + ", this.value, " + acc.getRoleId() + ")\">");
        out.println("           <option value=\"off\" class=\"select__option\"" + (acc.getAccStatus().equals("off") ? " selected style=\"color: red;\"" : "") + ">");
        out.println("               Dừng hoạt động</option>");
        out.println("           <option value=\"on\" class=\"select__option select__option--active\"" + (acc.getAccStatus().equals("on") ? " selected style=\"color: green;\"" : "") + ">");
        out.println("               Đang hoạt động</option>");
        out.println("        </select>");
        out.println("    </td>");
        out.println("    <td>" + acc.getAccCreatedDate()+ "</td>");
        out.println("    <td class=\"bold font-italic\">");
        if(acc.getRoleId() == 1)   out.println("    Admin");
        if(acc.getRoleId() == 2)   out.println("    Salesman");
        if(acc.getRoleId() == 3)   out.println("    Marketer");
        if(acc.getRoleId() == 4)   out.println("    Khách hàng");
        out.println("    </td>");
        out.println("    <td class=\"edit1\">");
        out.println("        <a href=\"ad_upuser?choice=view_detail&accId=" + acc.getAccId() + "\"><i class=\"far fa-eye fa-sm\" style=\"color: #b1a9a9;\"></i></a>");
        out.println("        <a href=\"#\" onclick=\"doDelete(event, " + acc.getAccId() + ")\"><i class=\"fas fa-trash-alt fa-sm\"");
        out.println("                                                            style=\"color: #eb2a2a;\"></i></a>");
        out.println("    </td>");
        out.println("</tr>");
    }

}
