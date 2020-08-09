/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.service.impl;

import com.frxs.msg.shift.dal.entity.Employee;
import com.frxs.msg.shift.dal.mapper.EmployeeMapper;
import com.frxs.msg.shift.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author ouyangzhaobing
 * @version : EmployeeServiceImpl.java,v 0.1 2020年07月28日 5:08 下午
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public Employee queryById(Integer id) {
        return employeeMapper.selectById(id);
    }
}