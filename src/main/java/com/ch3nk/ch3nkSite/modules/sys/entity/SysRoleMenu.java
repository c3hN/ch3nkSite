package com.ch3nk.ch3nkSite.modules.sys.entity;

import com.ch3nk.ch3nkSite.common.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * com.ch3nk.ch3nkSite.modules.sys.entity
 * chenkai
 */
@Getter
@Setter
public class SysRoleMenu extends BaseEntity {
    private String id;
    private String roleId;
    private SysMenu menu;
}
