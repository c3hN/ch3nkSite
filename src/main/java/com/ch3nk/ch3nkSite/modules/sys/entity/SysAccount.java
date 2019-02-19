package com.ch3nk.ch3nkSite.modules.sys.entity;

import com.ch3nk.ch3nkSite.common.base.entity.BaseEntity;
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
    private String  avator;
    private Integer level;
    private String  isAuth;
    private String  type;
    private String  state;
    private Date    createDate;
    private Date    modifyDate;
    private String  isDeleted;

}
