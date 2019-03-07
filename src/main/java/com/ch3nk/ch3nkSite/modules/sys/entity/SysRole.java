package com.ch3nk.ch3nkSite.modules.sys.entity;

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
public class SysRole extends BaseEntity{

    private String      roleId;
    private String      type;       //0:user 1:sys
    private String      name;
    private String      code;
    private String      description;
    private SysAccount  createBy;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date        createDate;
    private SysAccount  modifyBy;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date        modifyDate;
    private String      isDeleted;
    private Company     company;

    /**
     * 模糊查询字段
     */
    private String likeName;
    private String likeCode;
    private String likeType;

    public void beforeInsert() {
        SysAccount account = getCurrentAccount();
        Date date = new Date();
        setRoleId(UUIDutil.getUUID());
        setCreateBy(account);
        setCreateDate(date);
        setModifyBy(account);
        setModifyDate(date);
        setIsDeleted("0");
    }


    public void beforeUpdate() {
        SysAccount account = getCurrentAccount();
        Date date = new Date();
        setModifyBy(account);
        setModifyDate(date);
    }
}
