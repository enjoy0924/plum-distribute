package com.plum.filesystem.service;

import com.plum.filesystem.pojo.FSObject;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Andy on 2015/8/19.
 */
public interface IFileSystemService {

    /** 删除文件 */
    void deleteFile(String path);

    /** 创建文件 */
    String createFile(FileInputStream fileInputStream);

    /** 下载文件 */
    InputStream downloadFile(String path);

    /** 获取配置文件 */
    //Config getConfig();

    /** */
    boolean isFile();

    boolean isDir();

    /** 获取不包含路径的文字字符串 */
    String fileBaseName(String path);

    /** 改变文件所属的群组 */
    boolean chFileGroup(String filename, String group);

    /** 改变文件的所有者 */
    boolean chFileOwn(String filename, String user);

    boolean copyFile(String source, String destination);

    boolean renameFile(String filename, String newName);

    boolean moveFile(String source, String destination);

    List<FSObject> listDir(String path, boolean isReclusive);

    FSObject fsObject(String path);
}
