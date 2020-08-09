/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.dal.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 值班主题
 *
 * @author ouyangzhaobing
 * @version : Topic.java,v 0.1 2020年07月21日 3:05 下午
 */
@Data
@TableName("t_topic")
public class Topic {

    /**
     * id
     */
    @TableId
    private Integer id;

    /**
     * 主题名字
     */
    private String name;

    /**
     * 部门id
     */
    private Integer departmentId;

    private Date tmCreate;

    private Date tmSmp;
}