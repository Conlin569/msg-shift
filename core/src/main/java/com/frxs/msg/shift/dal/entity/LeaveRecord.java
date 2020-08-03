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
 * 请假记录表
 * @author ouyangzhaobing
 * @version : Leave.java,v 0.1 2020年07月21日 3:27 下午
 */
@Data
@TableName("t_leave")
public class LeaveRecord {

    /**
     * id
     */
    @TableId
    private Integer id;

    /**
     * 部门id
     */
    private Integer deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 请假人id
     */
    private Integer dutyManId;

    /**
     * 请假人姓名
     */
    private String dutyManName;

    /**
     * 状态
     * 补班完成or待完成
     *
     */
    private String status;

    private Date tmCreate;

    private Date tmSmp;
}