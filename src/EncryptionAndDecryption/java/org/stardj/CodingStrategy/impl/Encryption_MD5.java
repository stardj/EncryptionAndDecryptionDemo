package org.stardj.CodingStrategy.impl;

import org.stardj.CodingStrategy.IStrategy;

import java.security.MessageDigest;


/**
 * Created by stardj on 17/6/1.
 */
public class Encryption_MD5 implements IStrategy {

    @Override
    public void operate(String str) {
        encryptionByMD5(str);
    }

    private void encryptionByMD5(String context) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
        char[] charArray = context.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        String filename_MD5 = hexValue.toString();
    }

}
