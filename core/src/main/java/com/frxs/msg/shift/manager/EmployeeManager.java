/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.manager;

import com.frxs.msg.shift.dal.entity.Employee;

/**
 * @author ouyangzhaobing
 * @version : EmployeeManager.java,v 0.1 2020年08月03日 2:21 下午
 */
public interface EmployeeManager {

    /**
     * 获取该部门下的第一个员工
     *
     * @param departmentId
     * @return
     */
    Employee getFirstEmployeeByDepartmentId(Integer departmentId);

    /**
     * 查询该部门下该员工的下一位员工，当下一位员工为空时取该部门的第一个员工
     *
     * @param departmentId
     * @param employeeId
     * @return
     */
    Employee getNextEmployeeByEmployeeId(Integer departmentId, Integer employeeId);
}