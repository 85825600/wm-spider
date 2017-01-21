package com.bonc.spider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
	public static void main(String[] args) {
		String line = "http://m.ke.qq.com/cgi-bin/micromsg-bin/shakereport?is_ios=0&bkn=&_=1480304074069";
		String pattern = "/cgi-bin/micromsg-bin/(shakereport)$";

		Pattern r = Pattern.compile(pattern);

		Matcher m = r.matcher(line);
		if (m.find()) {
			System.out.println("KEYWORD: " + m.group(1));
		}
	}
}
