package com.plum.disk.constants;

/**
 * Created by Andy on 2015/8/19.
 */
public class CONST {

    /** 文件系统对象类型 **/
    public static final int FS_TYPE_DIRECTORY = 1;  //目录
    public static final int FS_TYPE_FILE      = 2;  //文件

    /** 文件系统对象的访问控制权限 **/
    public static final String FS_PERMISSION_ALL       = "all";        //完全控制
    public static final String FS_PERMISSION_READONLY  = "read-only";  //只读
    public static final String FS_PERMISSION_WRITEONLY = "write";      //可写入文件内容
    public static final String FS_PERMISSION_MODIFY    = "modify";     //可修改文件属性

    /** 文件系统对象的访问入口 **/
    public static final int FS_PERMISSION_ACCESS_ALLOWED      = 1;  //允许
    public static final int FS_PERMISSION_ACCESS_DENIED       = 2;  //禁止
    public static final int FS_PERMISSION_ACCESS_UNDETERMINED = 3;  //待定

    /** 默认授权对象 **/
    public static final String FS_PERMISSION_AUTH_ALL      = "WILDCARD";
    public static final String FS_PERMISSION_AUTH_ADMIN    = "ROLE_ADMINISTRATOR";
    public static final String FS_PERMISSION_AUTH_EVERYONE = "GROUP_EVERYONE";
    public static final String FS_PERMISSION_AUTH_OWNER    = "ROLE_OWNER";
    public static final String FS_PERMISSION_AUTH_GUEST    = "ROLE_GUEST";
}
