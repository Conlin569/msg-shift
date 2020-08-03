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
 * 值班计划
 *
 * @author ouyangzhaobing
 * @version : DutyPlan.java,v 0.1 2020年07月21日 3:20 下午
 */
@Data
@TableName("t_duty_plan")
public class DutyPlan {

    /**
     * id
     */
    @TableId
    private Integer id;

    /**
     * 主题id
     */
    private Integer topicId;

    /**
     * 主题名称
     */
    private String topicName;

    /**
     * 部门id
     */
    private Integer deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 执行时间
     */
    private Date startTime;

    /**
     * 发送周期
     */
    private Integer interval;

    private Date tmCreate;

    private Date tmSmp;
}