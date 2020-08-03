/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.chain.node;

import com.frxs.msg.shift.api.domain.PickRequest;
import com.frxs.msg.shift.chain.AbstractPickHandler;
import com.frxs.msg.shift.chain.PickContext;
import com.frxs.msg.shift.dal.entity.DutyRecord;
import com.frxs.msg.shift.dal.entity.Employee;
import com.frxs.msg.shift.dal.mapper.DutyRecordMapper;
import com.frxs.msg.shift.service.DepartmentService;
import com.frxs.msg.shift.service.DutyRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

/**
 * @author ouyangzhaobing
 * @version : ThisTimeDutyRecordHandler.java,v 0.1 2020年07月31日 11:02 上午
 */
@Slf4j
@Component
public class ThisTimeDutyRecordHandler extends AbstractPickHandler {

    @Resource
    private DutyRecordService dutyRecordService;

    @Resource
    private DutyRecordMapper dutyRecordMapper;

    @Resource
    private DepartmentService departmentService;

    @Override
    public void handlerRequest(PickContext pickContext) {
        dutyRecordMapper.insert(buildDutyRecord(pickContext));
        //dutyRecordService.save(buildDutyRecord(pickContext));
    }

    private DutyRecord buildDutyRecord(PickContext pickContext) {
        PickRequest pickRequest = pickContext.getPickRequest();
        DutyRecord dutyRecord = new DutyRecord();
        Integer departmentId = pickRequest.getDepartmentId();
        dutyRecord.setDepartmentId(departmentId);
        dutyRecord.setDepartmentName(departmentService.queryById(departmentId).getName());
        Employee nextEmployee = pickContext.getNextEmployee();
        dutyRecord.setEmployeeId(nextEmployee.getId());
        dutyRecord.setEmployeeName(nextEmployee.getName());
        dutyRecord.setStatus("NORMAL");
        //  判断是否第一次值班
        if (Objects.isNull(pickContext.getLastDutyDate())) {
            dutyRecord.setDutyDate(LocalDate.now());
        } else {
            //  值班时间应该保存为值班上次值班时间+(值班周期)
            LocalDate lastDutyDate = pickContext.getLastDutyDate();
            LocalDate date = lastDutyDate.plusDays(pickRequest.getCycle());
            dutyRecord.setDutyDate(date);
        }
        return dutyRecord;
    }
}