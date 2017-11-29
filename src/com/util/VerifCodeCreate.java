package com.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
/**
 * <p>ClassName: VerifCodeCreate</p>
 * <p>@Description:登录验证码生成方法</p>
 * <p>@author BianMingZhou</p>
 * <p>@date 2016-6-13下午5:11:13</p>
 */
public class VerifCodeCreate {
	
	public static final int WIDTH=120;
	public static final int HEIGHT=30;
	private Color[] colors = {Color.BLACK, Color.RED, Color.BLACK, Color.BLUE, Color.CYAN };
	@SuppressWarnings("unused")
	private String num = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private String str = "";
	/*public static void main(String[] args) {
		VerifCodeCreate verifCodeCreate=new VerifCodeCreate();
		
		System.out.println(verifCodeCreate.createImage());
	}*/
	public String getIDcode() {
		return str;
	}
	
	/**
	 * 得到图像
	 */
	public  BufferedImage createImage() {
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);
		Graphics g = image.getGraphics();
		str = "";
		setBackground(g);
		Color color = colors[new Random().nextInt(colors.length-1)];
		drawRandomLine(g,color);
		drawRandomNum(g,color);
		setBorder(g);
		return image;
	}

	/**
	 * 初始化边
	 * @param g
	 */
	private void setBorder(Graphics g) {
		
		
	}

	/**
	 * 画随即字
	 * @param g
	 * @param color
	 */
	private void drawRandomNum(Graphics g, Color color) {
		g.setFont(new Font("Arial", Font.BOLD, HEIGHT-2));
		g.drawRect(0, 0, WIDTH-1, HEIGHT-1);
	}

	/**
	 * 画随即线
	 * @param g
	 * @param color
	 */
	private void drawRandomLine(Graphics g, Color color) {
		
		
	}
	/**
	 * 初始化背景
	 * @param g
	 */
	private void setBackground(Graphics g) {
		g.setColor(Color.WHITE);
	}
}
