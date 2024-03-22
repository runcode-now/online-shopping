/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package com.team3.onlineshopping.filter;

import com.team3.onlineshopping.model.Account;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author PC
 */
public class SystemFilter implements Filter {

    private static final boolean debug = true;
    private static final String ACCESS_DENIAL_PAGE = "pub_accessdenial";

    private FilterConfig filterConfig = null;

    public SystemFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AdminFilter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AdminFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        if (debug) {
            log("AdminFilter:doFilter()");
        }

        doBeforeProcessing(request, response);

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getServletPath();

        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        if (uri.endsWith(".jsp")) {
            res.sendRedirect(ACCESS_DENIAL_PAGE);
            return;
        }

        //========================= ROLE GUEST ==========================
        if (account == null) {
            if (uri.equals("/com_upprofile") || uri.equals("/com_changepass")
                    || uri.equals("/com_upavar") || uri.equals("/com_logout")) {
                // Kiểm tra nếu người dùng không phải là quản trị viên
                res.sendRedirect(ACCESS_DENIAL_PAGE);
                return;
            }
        } else {
            if (uri.equals("/com_resetpass") || uri.equals("/com_newpass")
                    || uri.equals("/com_login")) {
                // Kiểm tra nếu người dùng không phải là quản trị viên
                res.sendRedirect(ACCESS_DENIAL_PAGE);
                return;
            }
        }

        //========================= ROLE ADMIN ==========================
        if (uri.equals("/ad_register") || uri.equals("/ad_userlist")
                || uri.equals("/ad_upuser") || uri.equals("/ad_dashboard")) {
            // Kiểm tra nếu người dùng không phải là quản trị viên
            if (account == null || account.getRoleId() != 1) {
                res.sendRedirect(ACCESS_DENIAL_PAGE);
                return;
            }
        }

        //========================= ROLE SALE ==========================
        if (uri.equals("/sale_order") || uri.equals("/sale_orderdetails")
                || uri.equals("/sale_filterorder") || uri.equals("/sale_deleteorder")
                || uri.equals("/sale_dashboard") || uri.equals("/sale_exportexcel")) {
            // Kiểm tra nếu người dùng không phải là quản trị viên
            if (account == null || account.getRoleId() != 2) {
                res.sendRedirect(ACCESS_DENIAL_PAGE);
                return;
            }
        }

        //========================= ROLE MKT ==========================
        if (uri.equals("/mkt_addproduct") || uri.equals("/mkt_delete")
                || uri.equals("/mkt_productdetail") || uri.equals("/mkt_productlist")
                || uri.equals("/mkt_cuslist") || uri.equals("/mkt_cusdetails")
                || uri.equals("/mkt_postcreate") || uri.equals("/mkt_postlist")
                || uri.equals("/mkt_postdetail") || uri.equals("/mkt_deletepost")
                || uri.equals("/mkt_postedit") || uri.equals("/mkt_sliderlist")
                || uri.equals("/mkt_sliderdetail") || uri.equals("/mkt_deleteslider")
                || uri.equals("/mkt_addslider") || uri.equals("/mkt_addcollection")
                || uri.equals("/mkt_collectionlist") || uri.equals("/mkt_feedbacklist")
                || uri.equals("/mkt_feedbackdeltai") || uri.equals("/mkt_dashboard")) {
            // Kiểm tra nếu người dùng không phải là quản trị viên
            if (account == null || account.getRoleId() != 3) {
                res.sendRedirect(ACCESS_DENIAL_PAGE);
                return;
            }
        }

        //========================= ROLE CUSTOMER ==========================
        if (uri.equals("/cus_upaddress") || uri.equals("/cus_cartcontact")
                || uri.equals("/cus_upadddetail") || uri.equals("/cus_deladdress")
                || uri.equals("/cus_myorder") || uri.equals("/cus_orderdetails")
                || uri.equals("/pub_cart") || uri.equals("/pub_addcart")
                || uri.equals("/pub_removecart") || uri.equals("/pub_completion")
                || uri.equals("/cus_feedback")) {
            // Kiểm tra nếu người dùng không phải là quản trị viên
            if (account == null || account.getRoleId() != 4) {
                res.sendRedirect(ACCESS_DENIAL_PAGE);
                return;
            }
        }

        //========================= ROLE GUEST ==========================
        if (uri.equals("/pub_viewproductlistbycategory") || uri.equals("/pub_searchproductbyproductname")
                || uri.equals("/pub_filterpriceandsizeproduct") || uri.equals("/pub_productdetails")
                || uri.equals("/pub_home") || uri.equals("/pub_viewpostlist")
                || uri.equals("/pub_postdetail")) {
            if (account != null && account.getRoleId() != 4) {
                // Kiểm tra nếu người dùng không phải là quản trị viên
                res.sendRedirect(ACCESS_DENIAL_PAGE);
                return;
            }
        }

        Throwable problem = null;

        try {
            chain.doFilter(request, response);
        } catch (Throwable t) {
            // If an exception is thrown somewhere down the filter chain,
            // we still want to execute our after processing, and then
            // rethrow the problem after that.
            problem = t;
            t.printStackTrace();
        }

        doAfterProcessing(request, response);

        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        if (problem
                != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("AdminFilter:Initializing filter");
            }
        }
    }

    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AdminFilter()");
        }
        StringBuffer sb = new StringBuffer("AdminFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
