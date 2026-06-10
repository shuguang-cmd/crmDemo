package com.xxx.crm.query;

import com.xxx.base.BaseQuery;

public class CustomerQuery extends BaseQuery {
    private String name;
    private String khno;
    private String level;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKhno() {
        return khno;
    }

    public void setKhno(String khno) {
        this.khno = khno;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
