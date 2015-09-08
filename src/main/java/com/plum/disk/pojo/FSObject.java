package com.plum.disk.pojo;

import java.util.List;

/**
 * Created by Andy on 2015/8/19.
 */
public class FSObject {
    private int type;
    private String name;
    private String path;
    private String size;
    private String creator;
    private String owner;
    private List<String> acl;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
