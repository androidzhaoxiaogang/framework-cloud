package cn.cloud.account.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/11/15.
 */

@Table(name = "menus")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    // 父菜单ID，一级菜单为0
    @Column(name="parent_id")
    private Long parentId;

    // 菜单名称
    @Column(name="name")
    private String name;

    // 菜单URL
    @Column(name="url")
    private String url;

    // 授权(多个用逗号分隔，如：user:list,user:create)
    @Column(name="perms")
    private String perms;

    // 类型 0：目录 1：菜单 2：按钮
    @Column(name="type")
    private Integer type;

    // 菜单图标
    @Column(name="icon")
    private String icon;

    // 排序
    @Column(name="order_num")
    private Integer orderNum;

    // 创建时间
    @Column(name="time_create")
    private Date timeCreate;

    // 修改时间
    @Column(name="time_update")
    private Date timeUpdate;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public Date getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Date timeCreate) {
        this.timeCreate = timeCreate;
    }

    public Date getTimeUpdate() {
        return timeUpdate;
    }

    public void setTimeUpdate(Date timeUpdate) {
        this.timeUpdate = timeUpdate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "icon='" + icon + '\'' +
                ", id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", perms='" + perms + '\'' +
                ", type=" + type +
                ", orderNum=" + orderNum +
                ", timeCreate=" + timeCreate +
                ", timeUpdate=" + timeUpdate +
                '}';
    }
}
