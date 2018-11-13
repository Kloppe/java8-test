package com.java.xinfu.core.io;

import java.io.File;
import java.io.IOException;

/**
 * @author 掘金
 * @description
 * @date 2018/11/10
 */
public interface Resource extends InputStreamSource{

    String getFileName();

    boolean exists();

    boolean isReadable();

    String getDescription();

    File getFile()throws IOException;

}
