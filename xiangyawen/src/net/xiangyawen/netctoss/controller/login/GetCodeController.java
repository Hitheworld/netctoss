package net.xiangyawen.netctoss.controller.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
public class GetCodeController {
	
	@RequestMapping(value="/getCode",method=RequestMethod.GET)
	public void execute(HttpServletRequest request,
			HttpServletResponse response,HttpSession session) throws IOException{
		//绘制一张图片的过程
		//1.准备一张空白的有尺寸的图片
		BufferedImage image = new BufferedImage(100, 30, BufferedImage.TYPE_INT_BGR);
		//2.获取图片的画笔对象
		Graphics g = image.getGraphics();
		//3.设置画笔颜色
		Random r = new Random();
		g.setColor(new Color(
				r.nextInt(255),
				r.nextInt(255),
				r.nextInt(255)
				));
		//4.绘制矩形实心的背景
		g.fillRect(0, 0, 100, 30);
		//5.更改画笔颜色
		g.setColor(new Color(255,255,255));
		//5-1.设置字体
		g.setFont(new Font(null,Font.BOLD,24));
		//6.绘制字符串
		String number = getNumber(1);
		//将图片字符存入session，用于检查验证码
		session.setAttribute("scode", number);
		//6-1.生成的验证码存到session中
		session = request.getSession();
		session.setAttribute("c", number);
		g.drawString(number, 5, 25);
		//7.设置响应流的数据格式
		response.setContentType("image/jpeg");
		//8.获取输出流
		OutputStream ops = response.getOutputStream();
		//9.保存图片到输出流中
		ImageIO.write(image, "jpeg", ops);
		//10.关闭流
		ops.close();
	}

	
	//绘制长度为5的随机字母+数字的组合的图片    方法
	public String getNumber(int size){
		String cs = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		String number = "";
		Random r = new Random();
		for(int i=0; i<size; i++){
			number += cs.charAt(r.nextInt(cs.length()));
		}
		return number;
	}

}
