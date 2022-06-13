package com.wang.mybatisplus.generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName user_info
 */
@Data
public class UserInfo implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 来源
     */
    private Integer source;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}