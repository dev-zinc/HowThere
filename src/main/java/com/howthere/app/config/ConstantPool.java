package com.howthere.app.config;

public class ConstantPool {
    private static final String WINDOW_FILE_ROOT = "C:/upload/";
    private static final String LINUX_FILE_ROOT = "/upload";

    public static String getFileRootPath() {
        final String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return WINDOW_FILE_ROOT;
        } else
            return LINUX_FILE_ROOT;
    }
}
