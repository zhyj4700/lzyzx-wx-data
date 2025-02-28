package com.yzsoft.wx.dao;

import com.yzsoft.wx.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author easyzzh
 * @since 2024-10-30
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 根据用户ID查询权限列表
     *
     * @param userId
     * @return
     */
    @Select("select DISTINCT p.id,p.parent_id,p.label,p.code,p.url,p.type,p.icon,p.remark,p.path,p.name\n" +
            "from sys_user as u\n" +
            "left join sys_user_role as ur on u.id = ur.user_id\n" +
            "left join sys_role as r on ur.role_id = r.id\n" +
            "left join sys_role_permission as rp on rp.role_id = r.id\n" +
            "left join sys_permission as p on rp.permission_id = p.id\n" +
            "where u.id =#{userId}\n" +
            "order by p.id asc")
    List<Permission> findPermissionListByUserId(@Param("userId") Long userId);
}
