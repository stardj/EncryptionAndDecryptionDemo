package org.stardj.Tools;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * 加密解密类，主要使用ES进行加密解密
 * <p>
 * Created by stardj on 2016/10/18.
 */
public class DESUtils {

    private static final String iKey = "1@3$5^7*";//默认密钥

    private Cipher iEncoding;
    private Cipher iDecoding;

    /**
     * 无参数构造方法
     *
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public DESUtils() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        this(iKey);//使用默认的key
    }

    /**
     * 有参数构造方法
     *
     * @param code 自定义的字符串密钥
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public DESUtils(String code) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {

        Key key = getKey(code.getBytes());

        iEncoding = Cipher.getInstance("DES");
        iEncoding.init(Cipher.ENCRYPT_MODE, key);

        iDecoding = Cipher.getInstance("DES");
        iDecoding.init(Cipher.DECRYPT_MODE, key);

    }


    /**
     * 将字符串密钥转换成加密算法可识别的密钥
     *
     * @param arrTmp
     * @return
     */
    private Key getKey(byte[] arrTmp) {
        //创建一个默认值为0的8位字节数组
        byte[] arr = new byte[8];

        //将原始数组转换为8位
        for (int i = 0; i < arr.length && i < arrTmp.length; i++) {
            arr[i] = arrTmp[i];
        }

//        System.out.println(new String(arr));//显示密钥

        //生成密钥
        Key key = new SecretKeySpec(arr,"DES");

        return key;
    }

    /**
     * 将二进制转换成十六进制
     *
     * @param buff
     * @return
     */
    private String parseByte2HexStr(byte[] buff){

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buff.length; i++) {
            String hex = Integer.toHexString(buff[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();

    }

    /**
     * 将十六进制转换成二进制
     *
     * @param hexStr
     * @return
     */
    private byte[] parseHexStr2Byte(String hexStr){

        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;

    }


    /**
     * 加密方法
     *
     * @param content
     * @return
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public String enCoding(String content) throws BadPaddingException, IllegalBlockSizeException {
        return parseByte2HexStr(iEncoding.doFinal(content.getBytes()));
    }



    /**
     * 解密方法
     *
     * @param content
     * @return
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public String deCoding(String content) throws BadPaddingException, IllegalBlockSizeException {
        return new String(iDecoding.doFinal(parseHexStr2Byte(content)));
    }


    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String testStr = "Java IO 同步与异步，阻塞和非阻塞的区别.md";
        DESUtils coding = new DESUtils();


        System.out.println(testStr);
        System.out.println(coding.enCoding(testStr));
        System.out.println(coding.deCoding(coding.enCoding(testStr)));

    }
}
