/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.service.impl;

import com.frxs.msg.shift.api.domain.EmployeeDto;
import com.frxs.msg.shift.service.DepartmentService;
import com.frxs.msg.shift.service.DutyRecordService;
import com.frxs.msg.shift.service.EmployeeService;
import com.frxs.msg.shift.service.ShiftService;
import com.frxs.msg.shift.dal.entity.DutyRecord;
import com.frxs.msg.shift.dal.entity.Employee;
import com.frxs.msg.shift.mapstruct.EmployeeMapStruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * @author ouyangzhaobing
 * @version : ShiftServiceImpl.java,v 0.1 2020年07月28日 4:05 下午
 */
@Slf4j
@Service
public class ShiftServiceImpl implements ShiftService {

    private static final String TIME_FORMATTER = "yyyy-MM-dd";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Resource
    private DepartmentService departmentService;

    @Resource
    private DutyRecordService dutyRecordService;

    @Resource
    private EmployeeService employeeService;


    @Override
    public EmployeeDto queryOnDuty(Integer departmentId, Integer times, Integer cycle) {
        EmployeeDto employeeDto = null;
        for (int i = 0; i <= times; i++) {
            employeeDto = pickNextOnDuty(departmentId, i);
            //  保存值班记录
            saveDutyRecord(employeeDto, i, cycle);
        }
        return employeeDto;
    }

    private EmployeeDto pickNextOnDuty(Integer departmentId, Integer times) {
        //  查询该部门是否有值班记录
        List<DutyRecord> dutyRecords = dutyRecordService.queryByDepartmentId(departmentId);
        if (dutyRecords.isEmpty()) {
            //  值班记录为空, 从该部门第一个人开始选人
            Employee employee = employeeService.queryFirstBydDepartmentId(departmentId);
            return EmployeeMapStruct.MAPPER.toEmployeeDto(employee);
        } else {
            //  有值班记录, 拿最近的一条记录
            DutyRecord dutyRecord = dutyRecordService.queryRecentByDepartmentId(departmentId);
            //  如果最近一条值班记录的日期就是今天那么直接返回这个人
            if (times == 0) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_FORMATTER);
                String dutyDate = simpleDateFormat.format(dutyRecord.getDutyDate());
                if (DATE_TIME_FORMATTER.format(LocalDate.now()).equals(dutyDate)) {
                    return EmployeeMapStruct.MAPPER.toEmployeeDto(employeeService.queryById(dutyRecord.getEmployeeId()));
                }
            }
            //  查询该部门是否有需要补班的人, 优先选补班的人

            //  查询该部门下这位值班员工的下一位员工
            Employee employee = employeeService.queryNextById(departmentId, dutyRecord.getEmployeeId());
            if (Objects.isNull(employee)) {
                //  下一位人为空, 意味着要从头开始轮流值班,
                employee = employeeService.queryFirstBydDepartmentId(departmentId);
                return EmployeeMapStruct.MAPPER.toEmployeeDto(employee);
            }
            return EmployeeMapStruct.MAPPER.toEmployeeDto(employee);
        }
    }

    private void saveDutyRecord(EmployeeDto employeeDto, Integer count, Integer cycle) {
        DutyRecord dutyRecord = new DutyRecord();
        dutyRecord.setDepartmentId(employeeDto.getDepartmentId());
        dutyRecord.setDepartmentName(departmentService.queryById(employeeDto.getDepartmentId()).getName());
        dutyRecord.setEmployeeId(employeeDto.getId());
        dutyRecord.setEmployeeName(employeeDto.getName());
        LocalDate date = LocalDate.now().plusDays(count * cycle);
        dutyRecord.setDutyDate(date);
        dutyRecord.setStatus("NORMAL");
        if (Objects.isNull(dutyRecordService.queryTodayOnDuty(employeeDto.getDepartmentId(), date))) {
            dutyRecordService.save(dutyRecord);
        }
    }
}