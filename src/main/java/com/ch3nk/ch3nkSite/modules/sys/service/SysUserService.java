package com.ch3nk.ch3nkSite.modules.sys.service;

import com.ch3nk.ch3nkSite.modules.sys.entity.SysUser;
import com.ch3nk.ch3nkSite.modules.sys.mapper.SysUserMapper;
import com.ch3nk.ch3nkSite.modules.utils.UUIDutil;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.management.relation.Role;
import java.io.IOException;
import java.util.*;

@Service
@Transactional(readOnly = true)
public class SysUserService implements ISysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> findUserByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<SysUser> userList = sysUserMapper.selectAll();
        return userList;
    }

    @Override
    public int findUserCount() {
        return sysUserMapper.selectCount();

    }

    @Override
    public SysUser findUserById(String userId) {
        return sysUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public SysUser findByAccount(String account) {
        return sysUserMapper.selectByAccount(account);
    }


    @Override
    public int saveUser(SysUser sysUser) {
        String nickName = sysUser.getNickName();
        String account = sysUser.getAccount();
        sysUser.setUserId(UUIDutil.getUUID());
        SimpleHash simpleHash = new SimpleHash("MD5", sysUser.getUserPwd());
        sysUser.setUserPwd(simpleHash.toHex());
        if (StringUtils.isBlank(nickName)){
            sysUser.setNickName("用户"+account);
        }
        sysUser.setCreateTime(new Date());
        sysUser.setUpdateTime(new Date());
        return sysUserMapper.insertSelective(sysUser);
    }

    @Override
    public int updateUser(SysUser sysUser) {
        return sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    @Override
    public int tombstone(String userId) {
//        sysUserMapper.selectByPrimaryKey(userId).getDeleteFlag()   //0:已删除  1：未删除
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        if (sysUser.getDeleteFlag() == 0) {
            return 1;
        }
        sysUser.setDeleteFlag(0);
        sysUser.setUpdateTime(new Date());
        return sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    /**
     * 用户导入
     * 模板：
     * 账号 昵称 密码
     * @param file
     * @return
     */
    @Override
    public List<SysUser> importUsersFromExc(MultipartFile file) {
        Map<String, String> result = new HashMap<>();
        int failureCount = 0;   //失败条数
        int successCount = 0;   //成功条数
        StringBuffer failureInfo = new StringBuffer();      //失败信息
        List<SysUser> all = sysUserMapper.selectAll();      //已存在的数据信息
        DataFormatter formatter = new DataFormatter();      //单元格数据格式化
        SysUser sysUser = null;
        List<SysUser> users = new ArrayList<>();
        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            outter:for (Row row : sheet) {     //循环行,一自动过滤空行
                if (row.getRowNum() == 0) {     //过滤第一行
                    continue;
                }
                for (SysUser user : all) {
                    if (StringUtils.equals(formatter.formatCellValue(row.getCell(0)),user.getAccount())) {  //account不能重复
                        failureCount += 1;
                        failureInfo.append("第"+row.getRowNum()+"行数据重复，"+
                                formatter.formatCellValue(row.getCell(0))+"已存在");
                        continue outter;
                    }
                }
                sysUser = new SysUser();
                sysUser.setUserId(UUIDutil.getUUID());
                sysUser.setAccount(formatter.formatCellValue(row.getCell(0)));
                sysUser.setNickName(formatter.formatCellValue(row.getCell(1)));
                sysUser.setCreateTime(new Date());
                sysUser.setUpdateTime(new Date());
                sysUser.setUserPwd(new SimpleHash("MD5",formatter.getDefaultFormat(row.getCell(2))).toHex());
                users.add(sysUser);
                successCount += 1;
            }
            return users;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void exportUsers2Exc() {

    }


}
