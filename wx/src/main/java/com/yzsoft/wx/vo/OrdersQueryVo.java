package com.yzsoft.wx.vo;

import lombok.Data;

@Data
public class OrdersQueryVo {
    private Long pageNo = 1L;//当前页码
    private Long pageSize = 200L;//每页显示数量
}
