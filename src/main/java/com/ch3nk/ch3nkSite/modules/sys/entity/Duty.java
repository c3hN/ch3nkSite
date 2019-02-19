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
public class Duty extends BaseEntity{

    private String  id;
    private String  name;
    private String  code;
    private String  attr;
    private String  type;
    private SysAccount createBy;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date    createDate;
    private SysAccount modifyBy;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date    modifyDate;
    private Integer modifyNum;
    private String  isDeleted;
}
