package org.stardj.Tools;

import java.io.*;

/**
 * Created by stardj on 17/5/31.
 */
public class FileProcessUtils {

    /**
     * 在文件头插入的字符串
     */
    private static final String KeyCode = "This is the KeyCode : stardjyeah@163.com!@#$%Longzhugtz2016.";

    /**
     * 加密文件
     *
     * 加密策略：在文件头插入一行字符串
     * @param file
     */
    public void encryptFiles(File file) {

        String path = file.getAbsolutePath().replace(file.getName(), "");
        String fileName = MD5Utils.string2MD5(file.getName());//md5加密文件名
        try {

            byte[] passwd = KeyCode.getBytes();

            //TODO 需加入文件名的hash算法以及数据库插入操作，加入多线程提升加密解密效率

            //获取原视频文件
            BufferedInputStream bisOld = new BufferedInputStream(new FileInputStream(file));
            //输出加密后的流文件
            BufferedOutputStream bosNew = new BufferedOutputStream(new FileOutputStream(new File(fileName)));//TODO 加密后的文件名  用filenameMD5做文件名
            //写入密钥
            bosNew.write(passwd);

            byte[] buffer = new byte[1024];
            int len = 0;
            //加密文件
            while ((len = bisOld.read(buffer)) > 0) {
                bosNew.write(buffer, 0, len);
            }

            bosNew.flush();
            bosNew.close();
            bisOld.close();
            file.delete();
        } catch (IOException e) {
            e.printStackTrace();


        } finally {


        }

    }

    /**
     * 解密文件
     *
     * 解密策略：去掉文件头的一行字符串
     * @param file
     */
    public void decrytFiles(File file) {

        String path = file.getAbsolutePath().replace(file.getName(), "");


        try {
            byte[] passwd = KeyCode.getBytes();

            //获取加密文件
            BufferedInputStream bisOld = new BufferedInputStream(new FileInputStream(file));
            //输出解密后流文件
            BufferedOutputStream bosNew = new BufferedOutputStream(new FileOutputStream(new File( path + "test.rmvb")));

            //获取密钥
            bisOld.read(passwd);
            passwd = null;

            byte[] buffer = new byte[1024];
            int len = 0;

            while ((len = bisOld.read(buffer)) > 0) {
                bosNew.write(buffer, 0, len);
            }

//            JDBCUtils.delete(ID);//删除数据库中对应的信息
            bosNew.flush();
            bosNew.close();
            bisOld.close();
            file.delete();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

    }

}
