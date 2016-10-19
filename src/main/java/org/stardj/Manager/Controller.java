package org.stardj.Manager;

import java.io.File;

/**
 * Created by stardj on 16/7/22.
 */
public class Controller {

    private static File[] readAll(String path) {
        File dirc = new File(path);
        File[] files = dirc.listFiles();
        return files;
    }

    public static void enCodingAllFiles(File file,int num){
        FileCoding fileCoding = new FileCoding();
        fileCoding.enCodingFile(file,num);
    }

    public static void deCodingAllFiles(File file){
        FileCoding fileCoding = new FileCoding();
        fileCoding.deCodingFile(file);
    }


    public static void main(String[] args) {

        String iPath = "/Users/stardj/Documents/testfiles/";//所要加密的文件所在目录
        File[] files = readAll(iPath);

        for (int i = 0; i < files.length; i++) {

            if(!(files[i].getName().equals(".DS_Store"))){//跳过mac中的这个文件，只针对mac

            System.out.println(i + " : " + files[i].getName());//显示所获取到的文件编号和文件名
//            enCodingAllFiles(files[i], i);//加密
//            deCodingAllFiles(files[i]);//解密

            }
        }

        System.out.println("DONE!");

    }
}
