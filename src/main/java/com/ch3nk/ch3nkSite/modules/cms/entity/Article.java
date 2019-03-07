package com.ch3nk.ch3nkSite.modules.cms.entity;

import com.ch3nk.ch3nkSite.common.base.entity.BaseEntity;
import com.ch3nk.ch3nkSite.common.serializer.JsonDateSerializer;
import com.ch3nk.ch3nkSite.modules.sys.entity.SysAccount;
import com.ch3nk.ch3nkSite.modules.utils.UUIDutil;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.SecurityUtils;

import java.util.Date;

/**
 * com.ch3nk.ch3nkSite.modules.cms.entity
 * chenkai
 */
@Getter
@Setter
public class Article extends BaseEntity {
    private String          arId;
    private String          name;
    private String          summary;
    private String          isOnline;
    private String          pic;
    private String          content;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date            publishTime;
    private SysAccount      createBy;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date            createDate;
    private SysAccount      modifyBy;
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date      modifyDate;
    private Integer         modifyNum;
    private String          isDeleted;


    public void beforeInsert() {
        SysAccount account = getCurrentAccount();
        Date date = new Date();
        setArId(UUIDutil.getUUID());
        setCreateBy(account);
        setCreateDate(date);
        setModifyBy(account);
        setModifyDate(date);
        setIsOnline("0");
        setModifyNum(0);
        setIsDeleted("0");
    }

    public void beforeUpdate(Article article) {
        SysAccount account = getCurrentAccount();
        Date date = new Date();
        setModifyBy(account);
        setModifyDate(date);
        setModifyNum(article.getModifyNum() + 1);
    }

}
