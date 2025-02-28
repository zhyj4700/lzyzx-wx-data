package com.yzsoft.wx.service;

import com.yzsoft.wx.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author easyzzh
 * @since 2024-10-30
 */
public interface UserService extends IService<User> {
    User findUserByUserName(String userName);
}
