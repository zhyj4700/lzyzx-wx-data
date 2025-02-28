package com.yzsoft.wx.service.impl;

import com.yzsoft.wx.entity.Permission;
import com.yzsoft.wx.dao.PermissionMapper;
import com.yzsoft.wx.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author easyzzh
 * @since 2024-10-30
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Override
    public List<Permission> findPermissionListByUserId(Long id) {
        return baseMapper.findPermissionListByUserId(id);
    }
}
