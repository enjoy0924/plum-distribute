package com.plum.cas.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="CAS_ROLE")
public class RoleEntity implements Serializable {
    private Long       id;          //编号
    private String     role;        //角色标识 程序中判断使用,如"admin"
    private String     description; //角色描述,UI界面显示使用
    //private List<Long> resourceIds; //拥有的资源
    private String     resourceIds;
    private Boolean    available = Boolean.FALSE; //是否可用,如果不可用将不会添加给用户

    public RoleEntity() {
    }

    public RoleEntity(String role, String description, Boolean available) {
        this.role = role;
        this.description = description;
        this.available = available;
    }

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name="resource_ids")
    public String getResourceIds(){
        return resourceIds;
    }
    public void setResourceIds(String resourceIds){
        this.resourceIds = resourceIds;
    }
//    public List<Long> getResourceIds() {
//        if(resourceIds == null) {
//            resourceIds = new ArrayList<Long>();
//        }
//        return resourceIds;
//    }
//
//    public void setResourceIds(List<Long> resourceIds) {
//        this.resourceIds = resourceIds;
//    }
//
//    public String getResourceIdsStr() {
//        if(resourceIds.isEmpty()) {
//            return "";
//        }
//        StringBuilder s = new StringBuilder();
//        for(Long resourceId : resourceIds) {
//            s.append(resourceId);
//            s.append(",");
//        }
//        return s.toString();
//    }
//
//    public void setResourceIdsStr(String resourceIdsStr) {
//        if(resourceIdsStr.isEmpty()) {
//            return;
//        }
//        String[] resourceIdStrs = resourceIdsStr.split(",");
//        for(String resourceIdStr : resourceIdStrs) {
//            if(resourceIdStr.isEmpty()) {
//                continue;
//            }
//            getResourceIds().add(Long.valueOf(resourceIdStr));
//        }
//    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleEntity role = (RoleEntity) o;

        if (id != null ? !id.equals(role.id) : role.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", description='" + description + '\'' +
                ", resourceIds=" + resourceIds +
                ", available=" + available +
                '}';
    }
}
