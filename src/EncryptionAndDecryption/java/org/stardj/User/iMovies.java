package org.stardj.User;

/**
 * Created by stardj on 16/7/22.
 */
public class iMovies {

    private String FileName;
    private String filenameMD5;

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public void setFilenameMD5(String filenameMD5) {
        this.filenameMD5 = filenameMD5;
    }

    public String getFileName() {
        return FileName;
    }

    public String getFilenameMD5() {
        return filenameMD5;
    }
}
