/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.manager.impl;

import com.frxs.msg.shift.dal.entity.Employee;
import com.frxs.msg.shift.dal.mapper.EmployeeMapper;
import com.frxs.msg.shift.manager.EmployeeManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author ouyangzhaobing
 * @version : EmployeeManagerImpl.java,v 0.1 2020年08月03日 2:23 下午
 */
@Component
public class EmployeeManagerImpl implements EmployeeManager {

    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public Employee getFirstEmployeeByDepartmentId(Integer departmentId) {
        return employeeMapper.selectFirstByDepartmentId(departmentId);
    }

    @Override
    public Employee getNextEmployeeByEmployeeId(Integer departmentId, Integer employeeId) {
        Employee employee = employeeMapper.selectNextById(departmentId, employeeId);
        if (Objects.isNull(employee)) {
            employee = getFirstEmployeeByDepartmentId(departmentId);
        }
        return employee;
    }
}