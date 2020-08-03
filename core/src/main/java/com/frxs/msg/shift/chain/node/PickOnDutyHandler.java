/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.chain.node;

import com.frxs.msg.shift.api.domain.PickRequest;
import com.frxs.msg.shift.chain.AbstractPickHandler;
import com.frxs.msg.shift.chain.PickContext;
import com.frxs.msg.shift.dal.entity.Employee;
import com.frxs.msg.shift.service.EmployeeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author ouyangzhaobing
 * @version : PickOnDutyHandler.java,v 0.1 2020年07月31日 10:47 上午
 */
@Component
public class PickOnDutyHandler extends AbstractPickHandler {

    @Resource
    private EmployeeService employeeService;

    @Override
    public void handlerRequest(PickContext pickContext) {
        Employee nextEmployee;
        PickRequest pickRequest = pickContext.getPickRequest();
        if (pickContext.getAgain()) {
            nextEmployee = employeeService.queryFirstBydDepartmentId(pickRequest.getDepartmentId());
        } else {
            //  获取该部门当前/上次当值班员工
            Employee nowEmployee = pickContext.getNowEmployee();
            //  根据部门id和该员工选取下次值班当员工
            nextEmployee = employeeService.queryNextById(pickRequest.getDepartmentId(), nowEmployee.getId());
        }
        //  设置下次值班人当id
        pickContext.setNextEmployee(nextEmployee);
        getNextHandler().handlerRequest(pickContext);
    }
}