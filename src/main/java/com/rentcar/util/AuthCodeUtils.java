package com.rentcar.util;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class AuthCodeUtils {
	public static void getVerificationCode(HttpServletResponse response, HttpServletRequest request) {

		try {
			// 定义图片的宽
			int width = 200;
			// 定义图片的高
			int height = 69;
			// 新建一个不透明的指定宽高的对象
			BufferedImage verifyImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			// 获取生成的随机验证码字符，并且将随机字符写进verifyImg对象中
			String randomText = getAuthCode(width, height, verifyImg);
			// 将随机验证码字符转换成小写放进session域中
			request.getSession().setAttribute("authCode", randomText.toLowerCase());
			// 必须设置响应内容类型为图片，否则前台不识别
			response.setContentType("image/png");
			// 获取文件输出流
			OutputStream os = response.getOutputStream();
			// 输出图片流
			ImageIO.write(verifyImg, "png", os);

			os.flush();
			// 关闭流
			os.close();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static String getAuthCode(int width, int height, BufferedImage verifyImg) {
		Graphics2D graphics = (Graphics2D) verifyImg.getGraphics();
		// 设置画笔颜色-验证码背景色
		graphics.setColor(Color.WHITE);
		// 填充背景
		graphics.fillRect(0, 0, width, height);

		graphics.setFont(new Font("微软雅黑", Font.BOLD, 40));

		// 数字和字母的组合

//		String baseNumLetter = "123456789abcdefghijklmnopqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";
		String baseNumLetter = "1234567890";

		StringBuffer sBuffer = new StringBuffer();
		// 旋转原点的 x 坐标
		int x = 10;

		String ch = "";

		Random random = new Random();

		for (int i = 0; i < 4; i++) {

			graphics.setColor(getRandomColor());

			// 设置字体旋转角度
			// 角度小于30度
			int degree = random.nextInt() % 30;
			int dot = random.nextInt(baseNumLetter.length());
			ch = baseNumLetter.charAt(dot) + "";
			sBuffer.append(ch);
			// 正向旋转
			graphics.rotate(degree * Math.PI / 180, x, 45);
			graphics.drawString(ch, x, 45);
			// 反向旋转
			graphics.rotate(-degree * Math.PI / 180, x, 45);
			x += 48;
		}
		// 画干扰线
		for (int i = 0; i < 6; i++) {
			// 设置随机颜色
			graphics.setColor(getRandomColor());
			// 随机画线
			graphics.drawLine(
					random.nextInt(width),
					random.nextInt(height),
					random.nextInt(width),
					random.nextInt(height));
		}
		// 添加噪点
		for (int i = 0; i < 30; i++) {
			int x1 = random.nextInt(width);
			int y1 = random.nextInt(height);
			graphics.setColor(getRandomColor());
			graphics.fillRect(x1, y1, 2, 2);
		}
		return sBuffer.toString();
	}

	private static Color getRandomColor() {
		Random ran = new Random();
		Color color = new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256));
		return color;
	}
}
