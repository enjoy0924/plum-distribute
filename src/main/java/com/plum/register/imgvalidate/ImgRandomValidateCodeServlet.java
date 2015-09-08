package com.plum.register.imgvalidate;

import com.plum.constant.CONST;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 *
 */
public class ImgRandomValidateCodeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private int width         = 80;//图片宽
    private int height        = 26;//图片高
    private int lineSize      = 40;//干扰线数量
    private int stringNum     = 4; //随机产生字符数量
    private String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";//随机产生的字符串
    public static String RANDOM_CODE_URL;


    @Override
    public void init(ServletConfig config) throws ServletException {
//        try {
//            super.init(config);
//            width = Integer.valueOf(config.getInitParameter("width"));
//            height = Integer.valueOf(config.getInitParameter("height"));
//            lineSize = Integer.valueOf(config.getInitParameter("lineSize"));
//            stringNum = Integer.valueOf(config.getInitParameter("stringNum"));
//            randString = config.getInitParameter("randString");
//
//            RANDOM_CODE_URL = config.getInitParameter("randomCodeURL");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        HttpSession session = request.getSession();
        ImgRandomValidateCode randomValidateCode = new ImgRandomValidateCode(width, height, lineSize, stringNum, randString);
        try {

            String randomCode = randomValidateCode.getRandomCode(response.getOutputStream());

            session.setAttribute(CONST.KEY_VALIDATE_IMG_CODE, randomCode);
            session.setAttribute(CONST.KEY_VALIDATE_IMG_RETRY_TIMES, 1);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
