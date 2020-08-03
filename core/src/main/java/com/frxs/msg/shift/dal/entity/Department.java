/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.dal.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 部门
 * @author ouyangzhaobing
 * @version : Dept.java,v 0.1 2020年07月21日 2:55 下午
 */
@Data
@TableName("t_department")
public class Department {

    /**
     * id
     */
    @TableId
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 机器人id
     */
    private String robotKey;

    private Date tmCreate;

    private Date tmSmp;


}