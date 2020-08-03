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
 * 人员
 * @author ouyangzhaobing
 * @version : DutyMan.java,v 0.1 2020年07月21日 2:46 下午
 */
@Data
@TableName("t_employee")
public class Employee {

    /**
     * id
     */
    @TableId
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 企业微信id
     */
    private String wechatWorkId;

    /**
     * 所属部门
     */
    private Integer departmentId;

    /**
     * 状态
     * 在职，请假
     */
    private String status;

    private Date tmCreate;

    private Date tmSmp;
}