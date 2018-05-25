package org.stardj.Manager.impl;

import org.stardj.CodingStrategy.impl.Encryption_DES;
import org.stardj.CodingStrategy.impl.Encryption_MD5;
import org.stardj.Manager.Context;
import org.stardj.Manager.FileProcessing;

import java.io.File;

/**
 * 对文件名进行处理
 *
 * Created by stardj on 17/6/1.
 */
public class FileNameProcessing implements FileProcessing{

    /**
     * 对文件名进行MD5加密
     * @param file
     */
    private void FileNameProcessing_MD5(File file){
        Context context = new Context(new Encryption_MD5());
        context.operate(file.getName());
    }

    /**
     * 对文件名进行DES加密
     * @param file
     */
    private void FileNameProcessing_DES(File file) {
        Context context = new Context(new Encryption_DES());
        context.operate(file.getName());
    }


    @Override
    public void fileProcessing(File file) {
        FileNameProcessing_MD5(file);//使用MD5加密
        FileNameProcessing_DES(file);//使用DES加密
    }
}
