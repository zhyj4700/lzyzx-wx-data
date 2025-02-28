package com.yzsoft.wx.service;

import com.yzsoft.wx.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author easyzzh
 * @since 2024-10-30
 */
public interface PermissionService extends IService<Permission> {
    List<Permission> findPermissionListByUserId(Long id);
}
