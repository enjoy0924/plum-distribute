package com.plum.disk.ac;

import com.plum.disk.constants.CONST;

/**
 * 访问控制对象，默认为【允许超级管理员拥有所有的控制权限】
 *
 * Created by Andy on 2015/8/19.
 */
public class ACE {

    private int accessMethod = CONST.FS_PERMISSION_ACCESS_ALLOWED;
    private String authority = CONST.FS_PERMISSION_AUTH_ADMIN;
    private String control   = CONST.FS_PERMISSION_ALL;  //这个控制权限可以是某个角色
    private boolean isInherited = false;  //如果这个值为true，权限继承

    public int getAccessMethod() {
        return accessMethod;
    }

    public void setAccessMethod(int accessMethod) {
        this.accessMethod = accessMethod;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public boolean isInherited() {
        return isInherited;
    }

    public void setIsInherited(boolean isInherited) {
        this.isInherited = isInherited;
    }
}
