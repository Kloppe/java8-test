package com.java.xinfu.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author 掘金
 * @description
 * @date 2018/11/10
 */
public interface InputStreamSource {
    InputStream getInputStream() throws IOException;
}
