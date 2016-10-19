package org.stardj.Manager;

import org.stardj.Tools.DESUtils;
import org.stardj.Tools.JDBCUtils;
import org.stardj.User.iMovies;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by stardj on 2016/10/18.
 */
public class FileCoding {

    private static final String KeyCode = "This is the KeyCode : stardjyeah@163.com!@#$%Longzhugtz2016.";

    private iMovies imovie;

    private DESUtils iDES;

    public void enCodingFile(File file,int num){

        try {
            iDES = new DESUtils();
            String path = file.getAbsolutePath().replace(file.getName(), "");
            imovie = new iMovies(num,iDES.enCoding(file.getName()));

            //将数据插入数据库
            JDBCUtils.insert(imovie);

            byte[] passwd = KeyCode.getBytes();

            //获取原视频文件
            BufferedInputStream bisOld = new BufferedInputStream(new FileInputStream(file));
            //输出加密后的流文件
            BufferedOutputStream bosNew = new BufferedOutputStream(new FileOutputStream(new File(path + num)));
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

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deCodingFile(File file){

        try {

            iDES = new DESUtils();
            int ID = Integer.valueOf(file.getName());
            String fileName = JDBCUtils.select(ID);
            String path = file.getAbsolutePath().replace(file.getName(), "");
            byte[] passwd = KeyCode.getBytes();

            //获取加密文件
            BufferedInputStream bisOld = new BufferedInputStream(new FileInputStream(file));
            //输出解密后流文件
            BufferedOutputStream bosNew = new BufferedOutputStream(new FileOutputStream(new File( path + iDES.deCoding(fileName))));
            //获取密钥
            bisOld.read(passwd);
            passwd = null;

            byte[] buffer = new byte[1024];
            int len = 0;

            while ((len = bisOld.read(buffer)) > 0) {
                bosNew.write(buffer, 0, len);
            }

            JDBCUtils.delete(ID);//删除数据库中对应的信息
            bosNew.flush();
            bosNew.close();
            bisOld.close();
            file.delete();

        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        String test = "/Users/stardj/Documents/testfiles/课表ielts.jpg";

        String test1= "/Users/stardj/Documents/testfiles/0";

        File file = new File(test1);

        FileCoding fileCoding = new FileCoding();

//        fileCoding.enCodingFile(file,0);

        fileCoding.deCodingFile(file);

    }

}
