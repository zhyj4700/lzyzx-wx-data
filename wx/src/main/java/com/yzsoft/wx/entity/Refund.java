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
@TableName("sys_refund")
public class Refund implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "order_no", type = IdType.AUTO)
    private String orderNo;

    private String refundNo;

    private String channelRefundNo;

    private String dealAmount;

    private String refundAmount;

    private String refundTime;

    private String orgName;

    private String orgId;

    private String updatedAt;

    private String refundChannel;


}
