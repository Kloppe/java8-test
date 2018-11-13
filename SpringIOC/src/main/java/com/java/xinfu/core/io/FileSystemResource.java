package com.java.xinfu.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author 掘金
 * @description
 * @date 2018/11/10
 */
public class FileSystemResource extends AbstractResource {
    private File file;
    private String path;

    public FileSystemResource(File file){
        this.file = file;
        this.path = file.getAbsolutePath();
    }

    public FileSystemResource(String path){
        this.path = path;
        this.file = new File(path);
    }


    @Override
    public String getFileName() {
        return this.file.getName();
    }

    @Override
    public String getDescription() {
        return "fileName: [" + this.file.getName() + "]" + " path: [" + this.path + "]";
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    public String getPath(){
        return path;
    }
}
