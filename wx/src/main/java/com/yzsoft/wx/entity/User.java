package com.yzsoft.wx.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 
 * </p>
 *
 * @author easyzzh
 * @since 2024-10-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
public class User implements Serializable , UserDetails {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;

    private boolean isAccountNonExpired;

    private boolean isAccountNonLocked;

    private boolean isCredentialsNonExpired;

    private boolean isEnabled;

    private String realName;

    private String nickName;

    private Long departmentId;

    private String departmentName;

    private Integer gender;

    private String phone;

    private String email;

    private String avatar;

    private Integer isAdmin;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer isDelete;
    /**
     * 权限列表
     */
    @TableField(exist = false)
    Collection<? extends GrantedAuthority> authorities;
    /**
     * 查询用户权限列表
     */
    @TableField(exist = false)
    private List<Permission> permissionList;
}
