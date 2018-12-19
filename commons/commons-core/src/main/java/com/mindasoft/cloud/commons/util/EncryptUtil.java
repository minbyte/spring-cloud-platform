package com.mindasoft.cloud.commons.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Administrator on 2016/5/11.
 */
public class EncryptUtil {
    public EncryptUtil() {}

    public static String Encrypt(String strSrc,String encName) {
        MessageDigest md=null;
        String strDes=null;


        try {
            byte[] bt=strSrc.getBytes("utf-8");
            if (encName==null||encName.equals("")) {
                encName="SHA-1";
            }
            md=MessageDigest.getInstance(encName);
            md.update(bt);
            strDes=bytes2Hex(md.digest());  //to HexString
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        return strDes;
    }

    public static String bytes2Hex(byte[]bts) {
        String des="";
        String tmp=null;
        for (int i=0;i<bts.length;i++) {
            tmp=(Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length()==1) {
                des+="0";
            }
            des+=tmp;
        }
        return des;
    }
    public static String generatePassportSignature(TreeMap<String,Object> paramMap, String key){
        StringBuilder rawSignature = new StringBuilder();
        Iterator<Map.Entry<String,Object>> it = paramMap.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry entry = it.next();
            String value = null;
            value = entry.getValue().toString();
            rawSignature.append(entry.getKey()).append("=").append(value).append("&");
        }

        rawSignature.append("secret_key=").append(key);
        System.out.println("待签名的字符####"+rawSignature.toString().toLowerCase());
        return EncryptUtil.Encrypt(rawSignature.toString().toLowerCase(),"SHA-1").toLowerCase();
    }

}
