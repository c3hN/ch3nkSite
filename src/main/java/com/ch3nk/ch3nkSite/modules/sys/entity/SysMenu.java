package com.ch3nk.ch3nkSite.modules.sys.entity;

import com.ch3nk.ch3nkSite.common.base.entity.BaseEntity;
import com.ch3nk.ch3nkSite.common.serializer.JsonDateSerializer;
import com.ch3nk.ch3nkSite.modules.utils.UUIDutil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.SecurityUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@SuppressWarnings("unused")
public class SysMenu extends BaseEntity {

    private String menuId;
    private String type;
    private String name;
    private String code;
    private String parentId;
    private String href;
    private SysAccount createBy;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date createDate;
    private SysAccount modifyBy;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date modifyDate;
    private Integer modifyNum;
    private String isDeleted;

    private String level;
    private String isLeaf;
    private String expanded;
    private List<SysMenu> children;

    public void beforeInsert() {
        SysAccount sysAccount = (SysAccount) SecurityUtils.getSubject().getPrincipal();
        Date currentDate = new Date();
        setCreateDate(currentDate);
        setModifyDate(currentDate);
        setCreateBy(sysAccount);
        setModifyBy(sysAccount);
        setModifyNum(0);
        setIsDeleted("0");
        setMenuId(UUIDutil.getUUID());
    }
    public void beforeUpdate(SysMenu sysMenu) {
        SysAccount sysAccount = (SysAccount) SecurityUtils.getSubject().getPrincipal();
        Date currentDate = new Date();
        setModifyBy(sysAccount);
        setModifyNum(sysMenu.getModifyNum()+1);
    }

}