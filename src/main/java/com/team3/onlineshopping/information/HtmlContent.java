/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.onlineshopping.information;

public class HtmlContent {

    public static String resetPassword() {
        String html = """
            <!DOCTYPE html>
            <html lang="en">
            
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
            </head>
            
            <body style="font-family: Arial, Helvetica, sans-serif;">
                <div class="image" style="width: 10rem;">
                    <img src="https://scontent.fhan14-1.fna.fbcdn.net/v/t1.15752-9/415986971_1701360590387678_3307011220705221556_n.png?_nc_cat=107&ccb=1-7&_nc_sid=8cd0a2&_nc_ohc=V2ZeUHI0viMAX-obv7t&_nc_oc=AQk00jCeUCuEPPd8XcE6oQs470-WNJk7DuFHo_dkl9lGsNgcp2dVsUo09mp_a5i8vgk&_nc_ht=scontent.fhan14-1.fna&oh=03_AdTSM_Cyy8H20BDq1THFcSkV3Eu-2-NLOAj0BMfPoy_lOA&oe=65D0D6FC"
                        alt="" style="width: 100%;">
                </div>
                <div class="container">
                    <h1>Thiết lập lại mật khẩu</h1>
                    <p>Bạn đã yêu cầu thiết lập lại mật khẩu. <br>
                        Vui lòng thiết lập lại mật khẩu của mình bằng cách click <strong>"Đặt lại mật khẩu"</strong> bên dưới:</p><br>
                    <a href="http://localhost:9999/onlineshopping/com_newpass" style="text-decoration: none;">
                        <p style="width: 10rem;background-color: rgb(22, 22, 175);
                        color: #FFF;text-align: center;padding: 10px 5px;
                        font-weight: bold;border-radius: 2px;">Đặt lại mật khẩu</p>
                    </a><br>
                    <i style="color: rgb(255, 0, 0);">(*) Lưu ý: Mã kích hoạt chỉ có hiệu lực trong vòng 5 phút</i>
                    <br><br><br>
                    <p>Trân trọng,</p>
                    <p>Ashion</p>
                </div>
            
            </body>
            </html>
        """;

        return html;
    }

    public static String orderNotification(String type, String payment) {
        String html = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "</head>\n"
                + "\n"
                + "\n"
                + "<body style=\"font-family: Arial, Helvetica, sans-serif;\">\n"
                + "    <div class=\"image\" style=\"width: 10rem;\">\n"
                + "        <img src=\"https://scontent.fhan14-1.fna.fbcdn.net/v/t1.15752-9/415986971_1701360590387678_3307011220705221556_n.png?_nc_cat=107&ccb=1-7&_nc_sid=8cd0a2&_nc_ohc=V2ZeUHI0viMAX-obv7t&_nc_oc=AQk00jCeUCuEPPd8XcE6oQs470-WNJk7DuFHo_dkl9lGsNgcp2dVsUo09mp_a5i8vgk&_nc_ht=scontent.fhan14-1.fna&oh=03_AdTSM_Cyy8H20BDq1THFcSkV3Eu-2-NLOAj0BMfPoy_lOA&oe=65D0D6FC\"\"\n"
                + "                                alt=\"\" style=\" width: 100%;\">\n"
                + "    </div>\n"
                + "    <div class=\"container\">\n"
                + "\n";
        if (type.equalsIgnoreCase("pending")) {
            html += "        <h1>Cảm ơn bạn đã đặt hàng.</h1>\n"
                    + "        <h3>Kính chào quý khách.</h3>\n"
                    + "        <p>Cảm ơn quý khách đã tín nghiệm dịch vụ của chúng tôi.</p>\n"
                    + "        <p>Chúng tôi sẽ nhanh chóng xử lý đơn hàng của quý khách.\n"
                    + "            <br>Trong thời gian chờ đợi, nếu có bất kỳ thắc mắc nào, vui lòng liên hệ với chúng tôi.\n"
                    + "        </p>\n"
                    + "        <p>Xin chân thành cảm ơn sự ủng hộ của bạn.</p>\n"
                    + "\n";
        } else if (type.equalsIgnoreCase("delivered")) {
            html += "<h1>Đơn hàng đã được giao thành công</h1>      \n"
                    + "        <p>Đơn hàng của bạn đã được bên vận chuyển báo giao thành công rồi ạ! Cảm ơn bạn đã ủng hộ chúng mình nha 💚\n"
                    + "            <br> 🌿 Nếu Bạn hài lòng hãy ĐÁNH GIÁ chúng mình 5 ⭐\n"
                    + "            <br> 💥 Nếu có bất kỳ vấn đề gì về đơn hàng (chưa nhận đc hàng, hàng lỗi, thiếu...),\n"
                    + "            <br> XIN ĐỪNG VỘI ĐÁNH GIÁ. Bạn hãy NHẮN TIN NGAY cho shop để shop xử lý ngay cho bạn nhé!\n"
                    + "            <br> 📞 Bạn vui lòng gọi Hotline nếu gấp nhé: 0387 609 907 ( 8h30-18h00 từ thứ 2 đến thứ 7)\n"
                    + "        </p> <br>\n"
                    + "        <p>Xin chân thành cảm ơn sự ủng hộ của bạn.</p>";
        } else if (type.equalsIgnoreCase("cancelled")) {
            html += "<h1>Đơn hàng của bạn đã được hủy</h1>\n"
                    + "        <p>Cảm ơn bạn đã theo dõi và ủng hộ shop🥰🥰🥰</p>\n"
                    + "        <p>Xin chân thành cảm ơn sự ủng hộ của bạn.\n"
                    + "            <br> Chúng tớ mong rằng bạn sẽ tiếp tục sử dụng dịch vụ của chúng tớ trong tương lai.\n"
                    + "        </p>\n"
                    + "\n";
            if (payment.equalsIgnoreCase("VNPAY")) {
                html += "<h4>Bạn vui lòng liên hệ chúng tớ để có thể nhận lại tiền đã chuyển nhé.</h4>";
            }
        }
        html += "        <br><br>\n"
                + "        <p>Trân trọng,</p>\n"
                + "        <p>Ashion</p>\n"
                + "    </div>\n"
                + "</body>\n"
                + "\n"
                + "\n"
                + "</html>";
        return html;
    }

}
