package com.ch3nk.ch3nkSite.modules.sys.entity;

import com.ch3nk.ch3nkSite.common.base.entity.BaseEntity;
import com.ch3nk.ch3nkSite.common.serializer.JsonDateSerializer;
import com.ch3nk.ch3nkSite.modules.utils.UUIDutil;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.SecurityUtils;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@SuppressWarnings("unused")
public class Company extends BaseEntity {

    private String  id;
    private String  fullName;
    private String  shortName;
    private String  enName;
    private String  code;
    private String  type;
    private String  scale;
    private SysAccount createBy;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date    createDate;
    private SysAccount modifyBy;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date    modifyDate;
    private Integer modifyNum;
    private String  isDeleted;

    public void beforeInsert() {
        SysAccount account = (SysAccount)SecurityUtils.getSubject().getPrincipal();
        Date currentDate = new Date();
        setId(UUIDutil.getUUID());
        setCreateBy(account);
        setModifyBy(account);
        setCreateDate(currentDate);
        setModifyDate(currentDate);
        setModifyNum(0);
        setIsDeleted("0");
    }

    public void beforeUpdate(Company company) {
        SysAccount account = (SysAccount)SecurityUtils.getSubject().getPrincipal();
        Date currentDate = new Date();
        setModifyBy(account);
        setModifyDate(currentDate);
        setModifyNum(company.getModifyNum() + 1);
    }

}
