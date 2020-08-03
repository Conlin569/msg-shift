/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.dal.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

/**
 * 值班历史
 * @author ouyangzhaobing
 * @version : DutyHistory.java,v 0.1 2020年07月21日 3:13 下午
 */
@Data
@TableName("t_duty_record")
public class DutyRecord {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 部门id
     */
    private Integer departmentId;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 值班人id
     */
    private Integer employeeId;

    /**
     * 值班人姓名
     */
    private String employeeName;

    /**
     * 值班日期
     */
    private LocalDate dutyDate;

    /**
     * 状态
     * 正常值班，补班
     */
    private String status;

    private Date tmCreate;

    private Date tmSmp;
}