/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.service.impl;

import com.frxs.msg.shift.api.domain.EmployeeDto;
import com.frxs.msg.shift.api.domain.PickRequest;
import com.frxs.msg.shift.api.exception.BizException;
import com.frxs.msg.shift.api.exception.enums.ErrorCodeDetailEnum;
import com.frxs.msg.shift.dal.entity.Department;
import com.frxs.msg.shift.dal.entity.DutyRecord;
import com.frxs.msg.shift.dal.entity.Employee;
import com.frxs.msg.shift.dal.entity.Topic;
import com.frxs.msg.shift.manager.DutyRecordManager;
import com.frxs.msg.shift.manager.EmployeeManager;
import com.frxs.msg.shift.mapstruct.EmployeeMapStruct;
import com.frxs.msg.shift.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


/**
 * @author ouyangzhaobing
 * @version : ShiftServiceImpl.java,v 0.1 2020年07月28日 4:05 下午
 */
@Slf4j
@Service
public class ShiftServiceImpl implements ShiftService {

    @Resource
    private DepartmentService departmentService;

    @Resource
    private DutyRecordService dutyRecordService;

    @Resource
    private EmployeeManager employeeManager;

    @Resource
    private DutyRecordManager dutyRecordManager;

    @Resource
    private EmployeeService employeeService;

    @Resource
    private TopicService topicService;

    @Override
    public EmployeeDto queryNthTimeOnDuty(PickRequest pickRequest) {
        //  部门id
        Integer departmentId = pickRequest.getDepartmentId();
        //  值班周期
        Integer cycle = pickRequest.getCycle();
        //  第n次
        Long nextTimes = pickRequest.getNextTimes();
        //  判断部门是否存在
        Department department = departmentService.queryById(departmentId);
        if (Objects.isNull(department)) {
            throw new BizException(ErrorCodeDetailEnum.PARAM_ERROR, "该部门id不存在!");
        }
        //查询该部门的模版
        Topic topic = topicService.queryByDepartmentId(departmentId);
        String topicName = topic.getName();
        String robotKey = department.getRobotKey();
        //  查询该部门是否有值班记录
        List<DutyRecord> dutyRecords = dutyRecordService.queryByDepartmentId(departmentId);
        if (dutyRecords.isEmpty()) {
            //  该部门没有值班人员, 第一次使用
            Employee employee = employeeManager.getFirstEmployeeByDepartmentId(departmentId);
            //  保存今天值班记录
            DutyRecord nowDuty = dutyRecordManager.buildDutyRecord(employee, department, LocalDate.now(), "NORMAL");
            dutyRecordService.save(nowDuty);
            for (int i = 0; i < nextTimes; i++) {
                DutyRecord dutyRecord = dutyRecordService.queryRecentByDepartmentId(departmentId);
                employee = employeeManager.getNextEmployeeByEmployeeId(departmentId, dutyRecord.getEmployeeId());
                //  保存第n次值班记录
                DutyRecord nThDuty = dutyRecordManager.buildDutyRecord(employee, department, LocalDate.now().plusDays(i + 1), "NORMAL");
                dutyRecordService.save(nThDuty);
            }
            EmployeeDto employeeDto = EmployeeMapStruct.MAPPER.toEmployeeDto(employee);
            employeeDto.setTopicName(topicName);
            employeeDto.setRobotKey(robotKey);
            return employeeDto;
        } else {
            //  查看是否已存在
            DutyRecord nThDutyRecord = dutyRecordService.queryByDepartmentIdAndDate(departmentId, LocalDate.now().plusDays(nextTimes));
            //  如果这个记录不存在, 那么就拿最近的一条值班记录，往上叠加就可以了
            if (Objects.isNull(nThDutyRecord)) {
                DutyRecord dutyRecord = dutyRecordService.queryRecentByDepartmentId(departmentId);
                LocalDate recentDate = dutyRecord.getDutyDate();
                LocalDate targetDate = LocalDate.now().plusDays(nextTimes);
//                System.out.println((targetDate.toEpochDay() - recentDate.toEpochDay()));
                Employee employee = null;
                for (int i = 0; i < (targetDate.toEpochDay() - recentDate.toEpochDay()); i++) {
                    DutyRecord nowDutyRecord = dutyRecordService.queryRecentByDepartmentId(departmentId);
                    employee = employeeManager.getNextEmployeeByEmployeeId(departmentId, nowDutyRecord.getEmployeeId());
                    DutyRecord nThDuty = dutyRecordManager.buildDutyRecord(employee, department, recentDate.plusDays(i + 1), "NORMAL");
                    dutyRecordService.save(nThDuty);
                }
                EmployeeDto employeeDto = EmployeeMapStruct.MAPPER.toEmployeeDto(employee);
                employeeDto.setTopicName(topicName);
                employeeDto.setRobotKey(robotKey);
                return employeeDto;
            } else {
                Employee employee = employeeService.queryById(nThDutyRecord.getEmployeeId());
                EmployeeDto employeeDto = EmployeeMapStruct.MAPPER.toEmployeeDto(employee);
                employeeDto.setTopicName(topicName);
                employeeDto.setRobotKey(robotKey);
                return employeeDto;
            }
        }
    }
}