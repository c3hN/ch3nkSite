package com.ch3nk.ch3nkSite.modules.sys.entity;

import com.ch3nk.ch3nkSite.common.base.entity.BaseEntity;
import com.ch3nk.ch3nkSite.common.serializer.JsonDateSerializer;
import com.ch3nk.ch3nkSite.modules.utils.UUIDutil;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * sys_operations
 * @author 
 */
@Getter
@Setter
@SuppressWarnings("unused")
public class SysOperate extends BaseEntity {

    private String  opId;
    private String  name;
    private String  code;
    private SysMenu  menu;
    private String  type;
    private String  url;
    private SysAccount createBy;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date    createDate;
    private SysAccount modifyBy;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date    modifyDate;
    private Integer modifyNum;
    private String  isDeleted;

    public void beforeInsert() {
        Date date = new Date();
        setOpId(UUIDutil.getUUID());
        setCreateDate(date);
        setModifyDate(date);
        setCreateBy(getCurrentAccount());
        setModifyBy(getCurrentAccount());
        setIsDeleted("0");
        setModifyNum(0);
    }

    public void beforeUpdate(){
        Date date = new Date();
        setModifyDate(date);
        setModifyBy(getCurrentAccount());
    }

}