package com.ch3nk.ch3nkSite.modules.sys.entity;

import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.ch3nk.ch3nkSite.common.base.entity.BaseEntity;
import com.ch3nk.ch3nkSite.common.serializer.JsonDateSerializer;
import com.ch3nk.ch3nkSite.modules.utils.UUIDutil;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@SuppressWarnings("unused")
public class SysAccount extends BaseEntity {

    private String  acId;
    private String  account;
    private String  password;
    private String  nickName;
    private String  avatar;
    private Integer level;
    private String  isAuth;
    private String  type;
    private String  state;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date    createDate;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date    modifyDate;
    private String  isDeleted;

    public void beforeInsert() {
        Date date = new Date();
        setAcId(UUIDutil.getUUID());
        setLevel(0);
        setIsAuth("0");
        setType("0");
        setState("0");
        setCreateDate(date);
        setModifyDate(date);
        setIsDeleted("0");
    }

    public void setBeforeUpdate() {
        Date date = new Date();
        setModifyDate(date);
    }
}
