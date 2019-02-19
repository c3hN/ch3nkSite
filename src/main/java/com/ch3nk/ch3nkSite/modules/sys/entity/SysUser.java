package com.ch3nk.ch3nkSite.modules.sys.entity;

import com.ch3nk.ch3nkSite.common.base.entity.BaseEntity;
import com.ch3nk.ch3nkSite.common.serializer.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 */
@Getter
@Setter
@SuppressWarnings("unused")
public class SysUser extends BaseEntity {

    private String      userId;
    private String      type;       //类别
    private String      name;       //名称
    private String      sex;        //性别
    private String      telephone;
    private String      phone;
    private String      idCard;
    private Department  dept;
    private String      mail;
    private String      qq;
    private String      createBy;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date        createDate;
    private Integer     modifyNum;
    private String      isDeleted;  //逻辑删除标记


    private String likeAccount;
    private String likeNickName;
    private String likeCreateTime;
}
