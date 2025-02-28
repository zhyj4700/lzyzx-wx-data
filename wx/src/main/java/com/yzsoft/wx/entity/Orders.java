package com.yzsoft.wx.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@TableName("sys_orders")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "order_no", type = IdType.AUTO)
    private String orderNo;

    private String parentOrderNo;

    private String userName;

    private String userNo;

    private String mchNo;

    private String mchName;

    private String deviceNo;

    private String orderAmount;

    private String dealAmount;

    private String dealTime;

    private String orderTime;

    private String payTime;

    private String payState;

    private String payChannel;

    private String refundTime;

    private String accId;

    private String channelNo;

    private String channelRefundNo;

    private String ext;

    private String discountAmount;

    private String overAmount;

    private String receiveAmount;

    private String scene;

    private String orgName;

    private String orgId;

    private String ticketNum;

    private String isDelay;

    private String updatedAt;

    private String accountsSource;


}
