package com.yzsoft.wx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yzsoft.wx.entity.Orders;
import com.yzsoft.wx.dao.OrdersMapper;
import com.yzsoft.wx.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author easyzzh
 * @since 2024-10-30
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Override
    public IPage<Orders> getOrdersListPyPage(IPage<Orders> page, String name, String startDealTime, String endDealTime, String userId, String payState) {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNotNull("order_no");
        queryWrapper.eq(name!=null,"user_name",name)
                .eq(userId!=null,"user_no",userId)
                .ge(startDealTime!=null,"deal_time",startDealTime)
                .le(endDealTime!=null,"deal_time",endDealTime)
                .eq(payState!=null,"pay_state",payState);
        return baseMapper.selectPage(page,queryWrapper);
    }
}
