/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.team3.onlineshopping.controllerCustomer;

import com.team3.onlineshopping.dal.AddressDAO;
import com.team3.onlineshopping.dal.CartDAO;
import com.team3.onlineshopping.dal.CustomerDAO;
import com.team3.onlineshopping.dal.OrderDetailsDAO;
import com.team3.onlineshopping.model.Cart;
import com.team3.onlineshopping.dal.ProductDAO;
import com.team3.onlineshopping.dal.ProductSizeDAO;
import com.team3.onlineshopping.information.Config;
import com.team3.onlineshopping.model.Account;
import com.team3.onlineshopping.model.Address;
import com.team3.onlineshopping.model.Customer;
import com.team3.onlineshopping.model.Order;
import com.team3.onlineshopping.model.OrderDetails;
import com.team3.onlineshopping.model.Product;
import com.team3.onlineshopping.model.ProductSize;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author minhb
 */
public class CusCartContactServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        CustomerDAO cus_dao = new CustomerDAO();
        CartDAO cartDao = new CartDAO();
        AddressDAO addressDao = new AddressDAO();
        ProductDAO proDao = new ProductDAO();
        List<Cart> lcart = new ArrayList<>();

        Account acc = (Account) session.getAttribute("account");
        String selectedValues = request.getParameter("selectedValues");
        String statusError = request.getParameter("status");
        String[] selectedArray = selectedValues.split(",");
        Customer cus = cus_dao.getByAccountId(acc.getAccId());

        System.out.println("status looi: " + statusError);
        for (String value : selectedArray) {
            int newV = Integer.parseInt(value);
            Cart nCart = new Cart();
            nCart = cartDao.getById(newV);
            lcart.add(nCart);
        }

        double totalPrice = 0;
        for (Cart value : lcart) {
            double proPrice = proDao.getById(value.getProId()).getProPrice();
            int proCost = value.getCartQuantity();
            totalPrice += proCost * proPrice;
        }
        totalPrice += 30000.0;
        List<Address> cusAddress = addressDao.getAllByCustomerId(cus.getCusId());

        if (statusError != null) {
            String[] Error = statusError.split(",");
            for (String statuss : Error) {
                if (statuss.equals("addressError")) {
                    request.setAttribute("addressError", "Vui lòng chọn địa chỉ giao hàng");
                }
                if (statuss.equals("paymentError")) {
                    request.setAttribute("paymentError", "Vui lòng chọn phương thức thanh toán");
                }
                if (statuss.equals("deliveryError")) {
                    request.setAttribute("deliveryError", "Vui lòng chọn phương thức giao hàng");
                }
            }
        }

        request.setAttribute("cusAddress", cusAddress);
        request.setAttribute("listcart", lcart);
        request.setAttribute("totalPrice", totalPrice);
        request.setAttribute("selectValues", selectedValues);

//        request.getRequestDispatcher("test.jsp").forward(request, response);
        request.getRequestDispatcher("cu_cartcontact.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        CustomerDAO cus_dao = new CustomerDAO();
        CartDAO cartDao = new CartDAO();
        AddressDAO addressDao = new AddressDAO();
        ProductDAO proDao = new ProductDAO();
        List<Cart> lcart = new ArrayList<>();
        AddressDAO add_dao = new AddressDAO();
        ProductDAO pro_dao = new ProductDAO();
        OrderDetailsDAO ordet_dao = new OrderDetailsDAO();
        ProductSizeDAO proSize_dao = new ProductSizeDAO();

        Account acc = (Account) session.getAttribute("account");
        String selectedValues = request.getParameter("selectedValues");
        String action = request.getParameter("action");
        String buynow = request.getParameter("buynow");
        String products = request.getParameter("product");
        String quantities = request.getParameter("quantity");
        String cateSizeId = request.getParameter("size");

        Customer cus = cus_dao.getByAccountId(acc.getAccId());
        double totalPrice = 0;

        if (buynow == null) {
            String[] selectedArray = selectedValues.split(",");
            for (String value : selectedArray) {
                int newV = Integer.parseInt(value);
                Cart nCart = new Cart();
                nCart = cartDao.getById(newV);
                lcart.add(nCart);
            }

            for (Cart value : lcart) {
                double proPrice = proDao.getById(value.getProId()).getProPrice();
                int proCost = value.getCartQuantity();
                totalPrice += proCost * proPrice;
            }

        } else {
            if (products != null) {
                int quantitys = Integer.parseInt(quantities);
                int productId = Integer.parseInt(products);
                totalPrice += (quantitys * proDao.getById(productId).getProPrice());

                Product pro = proDao.getById(productId);

                request.setAttribute("products", pro);
                request.setAttribute("quantity", quantities);
                request.setAttribute("cateSizeId", cateSizeId);
                request.setAttribute("buynow", "buynow");
            }

        }

        totalPrice += 30000.0;
        List<Address> cusAddress = addressDao.getAllByCustomerId(cus.getCusId());

        request.setAttribute("cusAddress", cusAddress);
        request.setAttribute("listcart", lcart);
        request.setAttribute("totalPrice", totalPrice);
        request.setAttribute("selectValues", selectedValues);
        request.setAttribute("selectedValues", selectedValues);

        //==================================create new address=========================================================
        if (action != null && action.equalsIgnoreCase("newAddress")) {
            String name = request.getParameter("name").trim();
            String phone = request.getParameter("phone").trim();
            String city = request.getParameter("city").trim();
            String district = request.getParameter("district").trim();
            String ward = request.getParameter("ward").trim();
            String detail = request.getParameter("detail").trim();

            name = (name == null) ? "" : name;
            phone = (phone == null) ? "" : phone;
            city = (city == null) ? "" : city;
            district = (district == null) ? "" : district;
            ward = (ward == null) ? "" : ward;
            detail = (detail == null) ? "" : detail;

            if (name.isEmpty() || !checkFormatPhone(phone) || city.isEmpty() || district.isEmpty() || ward.isEmpty() || detail.isEmpty()) {
                if (name.isEmpty()) {
                    request.setAttribute("nameError", "Tên không được để trống");
                } else {
                    request.setAttribute("addrName", name);
                }
                if (!checkFormatPhone(phone)) {
                    request.setAttribute("phoneError", "Số điện thoại phải gồm 10 chữ số và số 0 ở đầu");
                } else {
                    request.setAttribute("addrPhone", phone);
                }
                if (city.isEmpty()) {
                    request.setAttribute("cityError", "Thành phố không được để trống");
                } else {
                    request.setAttribute("city", city);
                }
                if (district.isEmpty()) {
                    request.setAttribute("districtError", "Quận không được để trống");
                } else {
                    request.setAttribute("district", district);
                }
                if (ward.isEmpty()) {
                    request.setAttribute("wardError", "Phường không được để trống");
                } else {
                    request.setAttribute("ward", ward);
                }
                if (detail.isEmpty()) {
                    request.setAttribute("detailError", "Chi tiết địa chỉ không được để trống");
                } else {
                    request.setAttribute("detail", detail);
                }
                request.setAttribute("addressAddNew", "on");
            } else {
                String newAdd = detail + ", " + ward + ", " + district + ", " + city;
                Address add = new Address(0, name, phone, newAdd, "", "on", cus.getCusId());
                List search = addressDao.getAllByCustomerId(cus.getCusId());

                if (search.size() != 0) {
                    add.setAddDefault("custom");
                } else {
                    add.setAddDefault("default");
                }
                for (Address value : cusAddress) {
                    if (add.equals(value)) {
                        addressDao.delete(value.getAddId());
                    }
                }

                addressDao.add(add);
                addressDao.deleteNull();
                List<Address> cusAddr = addressDao.getAllByCustomerId(cus.getCusId());
                request.setAttribute("cusAddress", cusAddr);
            }
            request.getRequestDispatcher("cu_cartcontact.jsp").forward(request, response);

        } //=====================================cart completion ================================================
        else if (action != null && action.equalsIgnoreCase("completion")) {
            String address_raw = request.getParameter("address");
            String[] productId = request.getParameterValues("product");
            String[] cart = request.getParameterValues("cart");
            String[] quantityArray = request.getParameterValues("quantity");
            String[] size = request.getParameterValues("size");
            double total = Double.parseDouble(request.getParameter("totalPrice"));
            String payment = request.getParameter("Payment");
            String delivery = request.getParameter("Delivery");

            List<String> status = new ArrayList<>();
            boolean errorStatus = false;
            String choicePay = null;
            String choiceDeli = null;
            String choiceAdd = null;
            String addressName = "";
            String addressPhone = "";

            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = currentDateTime.format(formatter);

            if (address_raw == null || address_raw.isEmpty() || address_raw.equals("")) {
                status.add("addressError");
                errorStatus = true;
                System.out.println("address Error");
            } else {
                String[] address = address_raw.split(",");
                int add = Integer.parseInt(address[2]);
                addressName = address[0];
                addressPhone = address[1];
                choiceAdd = Integer.toString(add);
            }

            if (payment == null || payment.isEmpty() || payment.equals("")) {
                status.add("paymentError");
                errorStatus = true;
                System.out.println("payment Error");
            } else {
                choicePay = payment;
            }

            if (delivery == null || delivery.isEmpty() || delivery.equals("")) {
                status.add("deliveryError");
                errorStatus = true;
                System.out.println("delivery Error");
            } else {
                choiceDeli = delivery;
            }

            if (errorStatus == true) {
                String statusError = ConvertArray(status);
                System.out.println(statusError);
                errorStatus = false;

                String[] Error = statusError.split(",");
                for (String statuss : Error) {
                    if (statuss.equals("addressError")) {
                        request.setAttribute("addressError", "Vui lòng chọn địa chỉ giao hàng");
                    }
                    if (statuss.equals("paymentError")) {
                        request.setAttribute("paymentError", "Vui lòng chọn phương thức thanh toán");
                    }
                    if (statuss.equals("deliveryError")) {
                        request.setAttribute("deliveryError", "Vui lòng chọn phương thức giao hàng");
                    }
                }
                if (choiceAdd != null) {
                    request.setAttribute("addressName", addressName);
                    request.setAttribute("addressPhone", addressPhone);
                    request.setAttribute("choiceAdd", choiceAdd);
                }
                if (choicePay != null) {
                    request.setAttribute("choicePay", choicePay);
                }
                if (choiceDeli != null) {
                    request.setAttribute("choiceDeli", choiceDeli);
                }

                request.getRequestDispatcher("cu_cartcontact.jsp").forward(request, response);
            } else {
                String[] address = address_raw.split(",");
                int add = Integer.parseInt(address[2]);

                Customer customer = cus_dao.getByAccountId(acc.getAccId());
                Address addr = add_dao.getById(add);

                Order or = new Order("Đơn hàng của " + addr.getAddRecName(), formattedDate, total, "pending", 1, customer.getCusId(), addr.getAddId());
                List<Integer> ordetail_quantity = new ArrayList<>();
                List<Integer> ordetail_size = new ArrayList<>();
                List<ProductSize> proSize_list = new ArrayList<>();
                List<Product> pro_list = new ArrayList<>();
                List<Integer> cart_list = new ArrayList<>();

                if (buynow == null) {
                    for (int i = 0; i < productId.length; i++) {
                        int proId = Integer.parseInt(productId[i]);
                        int quantity = Integer.parseInt(quantityArray[i]);
                        int cartId = Integer.parseInt(cart[i]);
                        int sizeId = Integer.parseInt(size[i]);

                        ProductSize proSize = proSize_dao.getByProCateSizeId(proId, sizeId);
                        Product pro = pro_dao.getById(proId);
                        proSize.setProSizeQuantity(proSize.getProSizeQuantity() - quantity);
                        pro.setProSold(pro.getProSold() + quantity);

                        ordetail_quantity.add(quantity);
                        ordetail_size.add(sizeId);
                        proSize_list.add(proSize);
                        pro_list.add(pro);
                        cart_list.add(cartId);
                    }
                } else {
                    int quantitys = Integer.parseInt(quantities);
                    int sizeId = Integer.parseInt(cateSizeId);
                    int prodId = Integer.parseInt(products);

                    Product pro = pro_dao.getById(prodId);
                    ProductSize proSize = proSize_dao.getByProCateSizeId(prodId, sizeId);
                    proSize.setProSizeQuantity(proSize.getProSizeQuantity() - quantitys);
                    pro.setProSold(pro.getProSold() + quantitys);

                    ordetail_quantity.add(quantitys);
                    ordetail_size.add(sizeId);
                    proSize_list.add(proSize);
                    pro_list.add(pro);
                }
                session.setAttribute("order", or);
                session.setAttribute("orderQuantity", ordetail_quantity);
                session.setAttribute("orderSize", ordetail_size);
                session.setAttribute("proSize", proSize_list);
                session.setAttribute("product", pro_list);
                session.setAttribute("cart", cart_list);
                session.setAttribute("total", total);

//                if (payment.equalsIgnoreCase("direct")) {
//                    session.setAttribute("payment", "direct");
//                    request.getRequestDispatcher("pub_completion").forward(request, response);
//                } else 
                    
                    if (payment.equalsIgnoreCase("VNPAY")) {
                    session.setAttribute("payment", "VNPAY");
                    String url = createPayment(request, response, total);
                    response.sendRedirect(url);
                }
            }
        } else {
            response.sendRedirect("p_notfound.jsp");
        }

    }

    //VNPAY
    public String createPayment(HttpServletRequest req, HttpServletResponse resp, double amo) throws UnsupportedEncodingException {
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";
        long amount = (long) amo * 100;
        String bankCode = "NCB";

        String vnp_TxnRef = Config.getRandomNumber(8);
        String vnp_IpAddr = Config.getIpAddress(req);

        String vnp_TmnCode = Config.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");

//        if (bankCode != null && !bankCode.isEmpty()) {
//            vnp_Params.put("vnp_BankCode", bankCode);
//        }
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);

        String locate = req.getParameter("language");
        if (locate != null && !locate.isEmpty()) {
            vnp_Params.put("vnp_Locale", locate);
        } else {
            vnp_Params.put("vnp_Locale", "vn");
        }
        vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;

        return paymentUrl;
    }

    public String ConvertArray(List<String> list) {
        String array = "";
        for (int i = 0; i < list.size(); i++) {
            array += list.get(i);
            if (i < list.size() - 1) {
                array += ",";
            }
        }
        return array;
    }

    private boolean checkFormatPhone(String phone) {
        // check format phone input
        return !phone.trim().isEmpty() && phone.matches("^0[35789]{1}\\d{8}$");
    }

}
