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
		//����һ��ͼƬ�Ĺ���
		//1.׼��һ�ſհ׵��гߴ��ͼƬ
		BufferedImage image = new BufferedImage(100, 30, BufferedImage.TYPE_INT_BGR);
		//2.��ȡͼƬ�Ļ��ʶ���
		Graphics g = image.getGraphics();
		//3.���û�����ɫ
		Random r = new Random();
		g.setColor(new Color(
				r.nextInt(255),
				r.nextInt(255),
				r.nextInt(255)
				));
		//4.���ƾ���ʵ�ĵı���
		g.fillRect(0, 0, 100, 30);
		//5.���Ļ�����ɫ
		g.setColor(new Color(255,255,255));
		//5-1.��������
		g.setFont(new Font(null,Font.BOLD,24));
		//6.�����ַ���
		String number = getNumber(1);
		//��ͼƬ�ַ�����session�����ڼ����֤��
		session.setAttribute("scode", number);
		//6-1.���ɵ���֤��浽session��
		session = request.getSession();
		session.setAttribute("c", number);
		g.drawString(number, 5, 25);
		//7.������Ӧ�������ݸ�ʽ
		response.setContentType("image/jpeg");
		//8.��ȡ�����
		OutputStream ops = response.getOutputStream();
		//9.����ͼƬ���������
		ImageIO.write(image, "jpeg", ops);
		//10.�ر���
		ops.close();
	}

	
	//���Ƴ���Ϊ5�������ĸ+���ֵ���ϵ�ͼƬ    ����
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
