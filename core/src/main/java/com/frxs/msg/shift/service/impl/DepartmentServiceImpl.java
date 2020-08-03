/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.service.impl;

import com.frxs.msg.shift.dal.mapper.DepartmentMapper;
import com.frxs.msg.shift.service.DepartmentService;
import com.frxs.msg.shift.dal.entity.Department;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ouyangzhaobing
 * @version : DepartmentServiceImpl.java,v 0.1 2020年07月28日 4:47 下午
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public Department queryById(Integer departmentId) {
        return departmentMapper.selectById(departmentId);
    }
}