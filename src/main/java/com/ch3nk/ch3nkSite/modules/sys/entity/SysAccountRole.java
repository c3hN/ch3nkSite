package com.ch3nk.ch3nkSite.modules.sys.entity;

import com.ch3nk.ch3nkSite.common.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * com.ch3nk.ch3nkSite.modules.sys.entity
 * chenkai
 */
@Getter
@Setter
public class SysAccountRole extends BaseEntity {

    private String id;
    private String acId;
    private SysRole role;
}
