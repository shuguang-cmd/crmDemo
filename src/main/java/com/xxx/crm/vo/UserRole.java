package com.xxx.crm.vo;

public class UserRole {
    //用户角色关联表
    private Integer id;
    private Integer userId;
    private Integer roleId;
    private String createDate;
    private String updateDate;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getRoleId() {
        return roleId;
    }

}
