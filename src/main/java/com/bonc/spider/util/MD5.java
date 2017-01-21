/*****************************************************************************
 * 东方国信通用爬虫项目[wm-spider]
 *----------------------------------------------------------------------------
 * com.bonc.spider.util.MD5.java
 *
 * @author andy
 * @date 2016年11月20日
 * @version 0.0.1
 * @since 0.0.1
 *----------------------------------------------------------------------------
 * (C) 北京东方国信科技股份有限公司
 *     Business-intelligence Of Oriental Nations Corporation Ltd. 2016
 *****************************************************************************/
package com.bonc.spider.util;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 * com.bonc.spider.util.MD5.java
 * 
 * @author andy
 * @date 2016年11月20日
 *
 * @since 0.0.1
 */
public class MD5 {

	/**
	 * 获取32位MD5码
	 * 
	 * @param plainText 需要加密的内容
	 * @return String
	 */
    public static String md5s32(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					buf.append("0");
				}
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
    
    /**
	 * 获取16位MD5码
	 * 
	 * @param plainText 需要加密的内容
	 * @return String
	 */
    public static String md5s16(String plainText){
        String str = md5s32(plainText);
        if(str == null){
            return null;
        }else{
            return str.substring(8, 24);
        }
    }

    /**
	 * 转换对象为字节数组
	 * 
	 * @param obj 需要转换的对象
	 * @return byte[]
	 */
    public static byte[] parseObjectToByte(java.lang.Object obj) {
        byte[] bytes = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            bytes = baos.toByteArray();
            baos.close();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }

}
