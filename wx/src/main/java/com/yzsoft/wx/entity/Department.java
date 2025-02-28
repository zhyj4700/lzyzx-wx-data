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
@TableName("sys_department")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String departmentName;

    private String phone;

    private String address;

    private Long pid;

    private String parentName;

    private Integer orderNum;

    private Integer isDelete;


}
