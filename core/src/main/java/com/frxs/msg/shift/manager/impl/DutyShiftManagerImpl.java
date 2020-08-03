/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.manager.impl;

import com.frxs.msg.shift.api.domain.EmployeeDto;
import com.frxs.msg.shift.api.domain.PickRequest;
import com.frxs.msg.shift.dal.entity.DutyRecord;
import com.frxs.msg.shift.dal.entity.Employee;
import com.frxs.msg.shift.manager.AbstractShift;
import com.frxs.msg.shift.mapstruct.EmployeeMapStruct;
import com.frxs.msg.shift.service.DepartmentService;
import com.frxs.msg.shift.service.DutyRecordService;
import com.frxs.msg.shift.service.EmployeeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author ouyangzhaobing
 * @version : DutyShiftManagerImpl.java,v 0.1 2020年08月01日 11:34 上午
 */
@Component
public class DutyShiftManagerImpl extends AbstractShift<EmployeeDto, PickRequest> {

    @Resource
    private EmployeeService employeeService;

    @Resource
    private DutyRecordService dutyRecordService;

    @Resource
    private DepartmentService departmentService;

    @Override
    public EmployeeDto pickOne(PickRequest pickRequest) {
        Integer departmentId = pickRequest.getDepartmentId();
        //  查询上一个值班人
        Employee lastEmployee = getLastOne(departmentId);
        Employee employee;
        if (Objects.isNull(lastEmployee)) {
            //  没有上一个值班人则直接选该部门的第一个人
            employee = employeeService.queryFirstBydDepartmentId(departmentId);
        } else {
            //  选中上一位值班人的下一位人
            employee = employeeService.queryNextById(departmentId, lastEmployee.getId());
        }
        return EmployeeMapStruct.MAPPER.toEmployeeDto(employee);
    }

    @Override
    protected void saveDutyRecord(EmployeeDto employeeDto, PickRequest pickRequest) {
        DutyRecord dutyRecord = new DutyRecord();
        Integer departmentId = pickRequest.getDepartmentId();
        dutyRecord.setDepartmentId(departmentId);
        dutyRecord.setDepartmentName(departmentService.queryById(departmentId).getName());
        dutyRecord.setEmployeeId(employeeDto.getId());
        dutyRecord.setEmployeeName(employeeDto.getName());
        dutyRecord.setStatus("NORMAL");
//        dutyRecord.setDutyDate(pickRequest);
        dutyRecordService.save(dutyRecord);
    }

    private Employee getLastOne(Integer departmentId) {
        DutyRecord dutyRecord = dutyRecordService.queryRecentByDepartmentId(departmentId);
        return Objects.isNull(dutyRecord) ? null : employeeService.queryById(dutyRecord.getEmployeeId());
    }
}