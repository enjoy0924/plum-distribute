package com.plum.filesystem.utils;

/**
 * Created by Andy on 2015/8/19.
 */
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

/**
 * 单例设计  提供Hadoop FileSystem 和 Configuration
 * @author TooCruel
 *
 */
public class HdfsUtils {
    private static Configuration conf = new Configuration();
    private static FileSystem fs = null;
    /**
     * 获取Hadoop的Configuration
     * @return
     */
    public static synchronized Configuration getConfiguration() {
        return conf;
    }

    /**
     * 该方法只会执行一次，即在第一次创建FileSystem时
     * @throws IOException
     * @throws URISyntaxException
     */
    private synchronized static void initFileSystem(String url) throws IOException, URISyntaxException{
        if(fs ==null)
            fs = FileSystem.get(new URI(url),conf);
    }
    public static FileSystem getFileSystem(String url) throws IOException, URISyntaxException{
        if(fs ==  null)//是第一次创建
            initFileSystem(url);
        return fs;
    }


    public synchronized static String convertSize(long size) {
        String result = String.valueOf(size);
        if (size < 1024 * 1024) {
            result = String.valueOf(size / 1024) + " KB";
        } else if (size >= 1024 * 1024 && size < 1024 * 1024 * 1024) {
            result = String.valueOf(size / 1024 / 1024) + " MB";
        } else if (size >= 1024 * 1024 * 1024) {
            result = String.valueOf(size / 1024 / 1024 / 1024) + " GB";
        } else {
            result = result + " B";
        }
        return result;
    }
}