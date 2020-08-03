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
import com.frxs.msg.shift.service.DutyRecordService;
import com.frxs.msg.shift.service.EmployeeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

/**
 * 查询
 *
 * @author ouyangzhaobing
 * @version : DutyRecordHandler.java,v 0.1 2020年07月31日 9:54 上午
 */
@Component
public class LastTimeDutyRecordHandler extends AbstractPickHandler {

    private static final String TIME_FORMATTER = "yyyy-MM-dd";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Resource
    private DutyRecordService dutyRecordService;

    @Resource
    private EmployeeService employeeService;

    @Override
    public void handlerRequest(PickContext pickContext) {
        PickRequest pickRequest = pickContext.getPickRequest();
        //  查询该部门上一条值班记录
        DutyRecord dutyRecord = dutyRecordService.queryRecentByDepartmentId(pickRequest.getDepartmentId());
        if (Objects.isNull(dutyRecord)) {
            //  该部门没有值班记录,
            pickContext.setAgain(true);
        } else {
            //  设置当前的值班员工
            Integer employeeId = dutyRecord.getEmployeeId();
            Employee employee = employeeService.queryById(employeeId);
            LocalDate dutyDate = dutyRecord.getDutyDate();
//            DutyRecord dutyRecord1 = dutyRecordService.queryTodayOnDuty(pickRequest.getDepartmentId(), LocalDate.now());
//            if (!Objects.isNull(dutyRecord1)) {
//                pickContext.setNextEmployee(employeeService.queryById(dutyRecord1.getEmployeeId()));
//                return;
//            }

            pickContext.setAgain(false);
            pickContext.setNowEmployee(employee);
            //  设置上次值班的日期
            pickContext.setLastDutyDate(dutyRecord.getDutyDate());
        }
        getNextHandler().handlerRequest(pickContext);
    }
}