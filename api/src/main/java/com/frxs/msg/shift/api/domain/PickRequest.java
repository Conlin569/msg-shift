/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.api.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ouyangzhaobing
 * @version : PickDto.java,v 0.1 2020年07月31日 9:42 上午
 */
@Data
public class PickRequest implements Serializable {

    /**
     * 部门id
     */
    private Integer departmentId;

    /**
     * 第nextTimes次的值班员工
     */
    private Integer nextTimes;

    /**
     * 该部门的值班周期
     */
    private Integer cycle;
}