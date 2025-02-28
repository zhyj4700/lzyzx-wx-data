package com.yzsoft.wx.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yzsoft.wx.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author easyzzh
 * @since 2024-10-30
 */
public interface OrdersService extends IService<Orders> {

    IPage<Orders> getOrdersListPyPage(IPage<Orders> page, String name, String startPayTime, String endPayTime, String userId, String payState);
}
