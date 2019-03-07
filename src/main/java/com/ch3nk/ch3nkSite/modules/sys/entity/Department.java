package com.ch3nk.ch3nkSite.modules.sys.entity;

import com.ch3nk.ch3nkSite.common.base.entity.BaseEntity;
import com.ch3nk.ch3nkSite.common.serializer.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@SuppressWarnings("unused")
public class Department extends BaseEntity {

    private String  deptId;
    private String  fullName;
    private String  shortName;
    private String  enName;
    private String  code;
    private Company company;
    private SysAccount createBy;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date    createDate;
    private SysAccount modifyBy;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date    modifyDate;
    private Integer modifyNum;
    private String  isDeleted;

    //模糊查询字段
    private String likeFullName;
    private String likeShortName;
    private String likeCode;
}
