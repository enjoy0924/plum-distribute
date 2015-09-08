package com.plum.register.imgvalidate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Random;

/**
 *
 */
public class ImgRandomValidateCode {

    private static Logger logger = LoggerFactory.getLogger(ImgRandomValidateCode.class);

    private Random random = new Random();

    private final int width; //图片宽
    private final int height;//图片高
    private final int lineSize;//干扰线数量
    private final int stringNum;//随机产生字符数量
    private final String randString;//随机产生的字符串

    public ImgRandomValidateCode(int width, int height, int lineSize, int stringNum, String randString) {
        this.width = width;
        this.height = height;
        this.lineSize = lineSize;
        this.stringNum = stringNum;
        this.randString = randString;
    }

    private Font getFont() {
        return new Font("Arial", Font.CENTER_BASELINE, 18);
    }

    private Color getRandColor(int fc, int bc) {
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    public String getRandomCode(OutputStream outputStream) {

        //BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();//产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 20));
        g.setColor(getRandColor(110, 133));
        //绘制干扰线
        for (int i = 0; i <= lineSize; i++) {
            drawLine(g);
        }
        //绘制随机字符
        String randomString = "";
        for (int i = 1; i <= stringNum; i++) {
            randomString = drawString(g, randomString, i);
        }
       System.out.println(randomString);

        //Log.info(logGetRandomCode + "randomCode:[" + randomString + "]");
        g.dispose();
        try {
            ImageIO.write(image, "JPEG", outputStream);//将内存中的图片通过流动形式输出到客户端
        } catch (Exception e) {
            e.printStackTrace();
        }

        return randomString;
    }

    /**
     * 绘制字符串
     *
     * @param g
     * @param randomString
     * @param i
     * @return
     */
    private String drawString(Graphics g, String randomString, int i) {
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random.nextInt(121)));
        String rand = String.valueOf(getRandomString(random.nextInt(randString.length())));
        randomString += rand;
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, 13 * i, 16);
        return randomString;
    }

    /**
     * 绘制干扰线
     *
     * @param g
     */
    private void drawLine(Graphics g) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }

    /**
     * 获取随机的字符
     *
     * @param num
     * @return
     */
    public String getRandomString(int num) {
        return String.valueOf(randString.charAt(num));
    }
}
