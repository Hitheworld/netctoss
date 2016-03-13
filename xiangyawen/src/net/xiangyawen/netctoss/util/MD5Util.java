package net.xiangyawen.netctoss.util;

import java.security.MessageDigest;

/**
 * MD5加密解密工具类
 * 解密须要调用二次才可以解密
 * @author Administrator
 *
 */
public class MD5Util {

	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return str;
	}
	
	
	public static String convertMD5(String inStr){  
		  
        char[] a = inStr.toCharArray();  
        for (int i = 0; i < a.length; i++){  
            a[i] = (char) (a[i] ^ 't');  
        }  
        String s = new String(a);  
        return s;  
  
    }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String name = "12345678";
		System.out.println(md5(name));
		System.out.println(convertMD5(convertMD5(name)));
	}

}
