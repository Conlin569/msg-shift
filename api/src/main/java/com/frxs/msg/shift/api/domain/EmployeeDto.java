/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.api.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ouyangzhaobing
 * @version : EmployeeDto.java,v 0.1 2020年07月28日 3:52 下午
 */
@Data
public class EmployeeDto implements Serializable {

    /**
     * 员工id
     */
    private Integer id;

    /**
     * 员工名字
     */
    private String name;

    /**
     * 企业微信id
     */
    private String wechatWorkId;

    /**
     * 所属部门id
     */
    private Integer departmentId;

    /**
     * 员工状态
     */
    private String status;
}