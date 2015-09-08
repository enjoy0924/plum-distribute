package com.plum.disk.service.impl;

import com.plum.disk.common.FileSystemConfig;
import com.plum.disk.pojo.FSObject;
import com.plum.disk.service.IFileSystemService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.List;

import com.plum.disk.utils.HdfsUtils;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 * Created by Andy on 2015/8/19.
 */
public class HdfsFileServiceImpl implements IFileSystemService {



    protected FileSystemConfig config ;
    protected FileSystem fs  ;

    public void deleteFile(String path) {
        Path hdfsPath =new Path( path);
        try {
            getFs().delete(hdfsPath, true);
        } catch (IOException e) {
            e.printStackTrace();
            //throw new HDFSException("HDFS异常", e);
        }
    }

    public String createFile(FileInputStream fileInputStream) {
        return null;
    }

    public InputStream downloadFile(String path) {
        return null;
    }

    public boolean isFile() {
        return false;
    }

    public boolean isDir() {
        return false;
    }

    public String fileBaseName(String path) {
        return null;
    }

    public boolean chFileGroup(String filename, String group) {
        return false;
    }

    public boolean chFileOwn(String filename, String user) {
        return false;
    }

    public boolean copyFile(String source, String destination) {
        return false;
    }

    public boolean renameFile(String filename, String newName) {
        return false;
    }

    public boolean moveFile(String source, String destination) {
        return false;
    }

    public List<FSObject> listDir(String path, boolean isReclusive) {
        return null;
    }

    public FSObject fsObject(String path) {
        return null;
    }

    public FileSystem getFs() {
        if(fs!=null) return fs;
        try {
            fs = HdfsUtils.getFileSystem(getConfig().getUrl());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return fs;
    }

    public void setFs(FileSystem fs) {
        this.fs = fs;
    }

    public FileSystemConfig getConfig() {
        return config;
    }

    public void setConfig(FileSystemConfig config) {
        this.config = config;
    }
}
