package com.shytong.core.util;


import org.apache.commons.codec.digest.DigestUtils;

import java.util.Random;

public class SyStringUtils extends org.apache.commons.lang.StringUtils {
	
	public static void main(String[] args)  {
		String l = "4.232138";
		String l1 = "2000000.0000000000000000000";
		System.out.println(unline2Hump("oooo_opp__p"));
		System.out.println(hump2Unline("userId,nickName"));
		System.out.println(md5("123456"));


	}

	public static final int hashIndex(Object key,int length) {


		return hash(key)&(length-1);
	}


	public static final int hash(Object key) {
		int h;
		return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
	}

	static  char md5String[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'a', 'b', 'c', 'd', 'e', 'f' };
	public static String md5(String str)  {



		return DigestUtils.md5Hex(str);
	}


	public static boolean  isBlank(Object str){

		if(str==null){
			return  true;
		}

		return isBlank(str.toString());

	}
	public static String  getString(Object str){
		if(isBlank(str)){

			return  null;
		}
		return str.toString();

	}
	public static String  isBlank(String str,String defaultStr){
		if(isBlank(str)){

			return  defaultStr;
		}
		return str;

	}
	public static String hump2Unline(String str){

		StringBuilder b=new StringBuilder();
		boolean flag=false;
		char c;

		for(int n=0;n<str.length();n++){
			c=str.charAt(n);

			if (c >= 'A' && c <= 'Z'){


				b.append('_');
				b.append((char)(c+32));
			}else{
				b.append(c);
			}




		}


		return b.toString();
	}
	public static String unline2Hump(String str){

		StringBuilder b=new StringBuilder();
		boolean flag=false;
		char c;
		   for(int n=0;n<str.length();n++){
			   c=str.charAt(n);
                   if(c=='_'){

					   flag=true;
					   continue;
				   }

			       if(flag){

					   if (c >= 'a' && c <= 'z'){

						   b.append((char)(c-32));

					   }

					   flag=false;

                   } else {

					   b.append(str.charAt(n));
				   }







		   }


		return b.toString();
	}


	public  static String randNum(int len){

		Random rand = new Random(System.currentTimeMillis());
		StringBuilder verifyCode = new StringBuilder(len);
		for(int i = 0; i < len; i++){
			verifyCode.append(rand.nextInt(10));
		}
		return verifyCode.toString();
	}
	   
}
