package org.stardj.Manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by stardj on 16/7/22.
 */
public class Controller {

    public static ArrayList<String> result = new ArrayList<>();
    private static File[] readAll(String path) {
        File dirc = new File(path);
        File[] files = dirc.listFiles();
        return files;
    }

    public static void enCodingAllFiles(File file, int num) {
        FileCoding fileCoding = new FileCoding();
        fileCoding.enCodingFile(file, num);
    }

    public static void deCodingAllFiles(File file) {
        FileCoding fileCoding = new FileCoding();
        fileCoding.deCodingFile(file);
    }

    public static String[] getFileName(String path) {
        File file = new File(path);
        String[] fileName = file.list();
        return fileName;
    }

    public static void getAllFileName(String path, ArrayList<String> fileName) {
        File file = new File(path);
        File[] files = file.listFiles();
        String[] names = file.list();
        if (names != null)
            fileName.addAll(Arrays.asList(names));
        for (File a : files) {
            if (a.isDirectory()) {
                getAllFileName(a.getAbsolutePath(), fileName);
            } else {
//                result.add(a.getAbsolutePath());
                try {
                    System.out.println(a.getCanonicalPath().replace("/Users/stardj/windows/银联无跳转支付/tokenpay/unionTokenPay.Front3/.build/",""));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {

//        String iPath = "/Users/stardj/Documents/testfiles/";//所要加密的文件所在目录
        String iPath = "/Users/stardj/windows/银联无跳转支付/tokenpay/unionTokenPay.Front3/.build";
//        File[] files = readAll(iPath);

//        for (int i = 0; i < files.length; i++) {
//
//
//            if (!(files[i].getName().equals(".DS_Store"))) {//跳过mac中的这个文件，只针对mac
//
//                System.out.println(i + " : " + files[i].getName());//显示所获取到的文件编号和文件名
////            enCodingAllFiles(files[i], i);//加密
////            deCodingAllFiles(files[i]);//解密
//
//
//            }
//        }

        String [] fileName = getFileName(iPath);
        for(String name:fileName)
        {
            System.out.println(name);
        }
        System.out.println("--------------------------------");
        ArrayList<String> listFileName = new ArrayList<String>();
        getAllFileName(iPath,listFileName);
//        for(String name:listFileName)
//        {
//            System.out.println(name);
//        }

        System.out.println("DONE!");

    }
}
