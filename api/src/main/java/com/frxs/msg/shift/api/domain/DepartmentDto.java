/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.api.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ouyangzhaobing
 * @version : DepartmentDto.java,v 0.1 2020年07月28日 3:56 下午
 */
@Data
public class DepartmentDto implements Serializable {


    /**
     * 部门id
     */
    private Integer id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 企业微信机器人key
     */
    private String robotKey;
}