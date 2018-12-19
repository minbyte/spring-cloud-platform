package com.mindasoft.cloud.commons.util;

import javax.crypto.Cipher;
import java.io.*;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class RSAUtil {

    /**
     * RSA largest encrypt block size
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /** @description RSA algorithm type */
    public static final String KEY_ALGORITHM = "RSA/ECB/PKCS1Padding";

    public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {

        byte[] keyBytes = Base64.decode(publicKey);
        ByteArrayInputStream bins = new ByteArrayInputStream(keyBytes);
        BufferedInputStream bis = new BufferedInputStream(bins);
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        Certificate cert = cf.generateCertificate(bis);
        PublicKey publicK = cert.getPublicKey();

        // encrypt
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // encrypt by size
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /**
     * 根据公钥地址获取公钥
     * @param publicKeyPath
     * @return
     * @throws IOException
     * @throws CertificateException
     */
    public static PublicKey getPublicKey(String publicKeyPath) throws IOException, CertificateException {
        FileReader fr = new FileReader(new File(publicKeyPath));
        BufferedReader br = new BufferedReader(fr);
        StringBuilder sb=new StringBuilder();
        String str;
        while((str=br.readLine())!=null ){
            str = str.replaceAll("\n", "").trim();
            sb.append(str);
        }
        br.close();
        fr.close();

        byte[] keyBytes = Base64.decode(sb.toString());
        ByteArrayInputStream bins = new ByteArrayInputStream(keyBytes);
        BufferedInputStream bis = new BufferedInputStream(bins);
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        Certificate cert = cf.generateCertificate(bis);
        return  cert.getPublicKey();
    }
    public static String encryptByPublicKey(byte[] data, PublicKey publicKey) throws Exception {

        // encrypt
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // encrypt by size
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return Base64.encodeBytes(encryptedData);
    }

    // rsa key split head length
    public static int HEAD = 27;

    // rsa key split end length
    public static int END = 25;

    public static String getKey() {
        String responseString = "-----BEGIN CERTIFICATE----- MIIChTCCAe4CCQCu4R8FT/ZexDANBgkqhkiG9w0BAQUFADCBhjELMAkGA1UEBhMC Q04xFDASBgNVBAgTC2h1bmFudHYuY29tMQswCQYDVQQHEwJjczEQMA4GA1UEChMH aHVuYW50djEQMA4GA1UECxMHaHVuYW50djEMMAoGA1UEAxMDaWRwMSIwIAYJKoZI hvcNAQkBFhNza3kyMDA4MTgxNkAxMjYuY29tMB4XDTEyMTEyNzAyMjQxNFoXDTEz MTEyNzAyMjQxNFowgYYxCzAJBgNVBAYTAkNOMRQwEgYDVQQIEwtodW5hbnR2LmNv bTELMAkGA1UEBxMCY3MxEDAOBgNVBAoTB2h1bmFudHYxEDAOBgNVBAsTB2h1bmFu dHYxDDAKBgNVBAMTA2lkcDEiMCAGCSqGSIb3DQEJARYTc2t5MjAwODE4MTZAMTI2 LmNvbTCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEAm2+MEhINcxuks6Fq4XAc A0WCcbT7puYN/JKfFcYpDo0tOP+u3TKxCHAP6nopfZ/S/VRionlkXMlyYFP2RPVN +chOS5WWGLDSvhY+0jKcA1w9OsXVkPGNKkugXNb0yiT0+rX0Vv2TySyys+hg1ptx 13J4XeoCJc41s0bv3vjcB40CAwEAATANBgkqhkiG9w0BAQUFAAOBgQBgmsXuKqRj jMw6rqcTUuuescWR6bt8eVVJSing8mxTgtPSa4DvHQWOFowAnHhYlZiP5JicUR59 tPTOdTkkLtrNYBwF492p9w5lsUFbOd7fA5IHBtcXXWWES/zLlsFoZyrxNu8XFaYt uqRfQDqm3j4y7yXoAzZ8fdzA5DpHR/7spw== -----END CERTIFICATE-----";
        if (responseString.length() > (HEAD + END)) {
            responseString = responseString.substring(HEAD);
            responseString = responseString.substring(0, responseString.length() - END);
            String key = responseString.replaceAll("\n", "").trim();
            System.out.println(key);
            return key;
        } else {
            return "";
        }
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
        return EncryptUtil.Encrypt(rawSignature.toString().toLowerCase(),"SHA-1").toLowerCase();
    }

    public static String generateSHA1Signature(TreeMap<String,Object> paramMap){
        StringBuilder rawSignature = new StringBuilder();
        Iterator<Map.Entry<String,Object>> it = paramMap.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry entry = it.next();
            String value = null;
            if(null == entry.getValue()){
                value = "";
            }else{
                value = entry.getValue().toString();
            }
            rawSignature.append(entry.getKey().toString().toLowerCase()).append("=").append(value).append("&");
        }
        String str = rawSignature.toString();
        str = str.substring(0,str.length()-1);
        return EncryptUtil.Encrypt(str,"SHA-1").toLowerCase();
    }
}
