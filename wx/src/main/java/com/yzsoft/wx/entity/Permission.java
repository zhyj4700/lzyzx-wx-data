package com.yzsoft.wx.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
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
@TableName("sys_permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String label;

    private Long parentId;

    private String parentName;

    private String code;

    private String path;

    private String name;

    private String url;

    private Integer type;

    private String icon;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String remark;

    private Integer orderNum;

    private Integer isDelete;


}
