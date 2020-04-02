package com.jiker.bcp.user.entity;

/**
 * @author jiker.luo
 * @date 2020/4/1
 */
public class UserInfo extends LongIdEntity {

    /**
     * 用户名
     */
    private Long username;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 联系电话
     */
    private Long contactPhone;

    /**
     * 电子邮箱
     */
    private String mail;

    /**
     * 学号/工号
     */
    private Long workNumber;

    /**
     * 是否为管理员：0 否；1 是
     */
    private Integer roleAdmin;

    /**
     * 审核状态：-1 审核驳回；1 待初审 2 审核通过
     */
    private Integer verify;

    /**
     * 审核驳回原因
     */
    private String refusalReason;

    /**
     * 用户头像
     */
    private String pictureUrl;

    /**
     * 收藏博客
     */
    private String collectionBlog;

    /**
     * 允许进入的房间
     */
    private String collectionRoom;

    public Long getUsername() {
        return username;
    }

    public void setUsername(Long username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(Long contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Long getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(Long workNumber) {
        this.workNumber = workNumber;
    }

    public Integer getRoleAdmin() {
        return roleAdmin;
    }

    public void setRoleAdmin(Integer roleAdmin) {
        this.roleAdmin = roleAdmin;
    }

    public Integer getVerify() {
        return verify;
    }

    public void setVerify(Integer verify) {
        this.verify = verify;
    }

    public String getRefusalReason() {
        return refusalReason;
    }

    public void setRefusalReason(String refusalReason) {
        this.refusalReason = refusalReason;
    }

    public String getPicture_url() {
        return pictureUrl;
    }

    public void setPicture_url(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getCollectionBloc() {
        return collectionBlog;
    }

    public void setCollectionBloc(String collectionBlog) {
        this.collectionBlog = collectionBlog;
    }

    public String getCollectionRoom() {
        return collectionRoom;
    }

    public void setCollectionRoom(String collectionRoom) {
        this.collectionRoom = collectionRoom;
    }
}
