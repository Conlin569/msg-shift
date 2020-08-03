/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.service;

import com.frxs.msg.shift.dal.entity.Department;

/**
 * @author ouyangzhaobing
 * @version : DutyService.java,v 0.1 2020年07月22日 10:06 上午
 */
public interface DepartmentService {

    /**
     * 根据部门id查询部门
     *
     * @param departmentId
     * @return
     */
    Department queryById(Integer departmentId);
}