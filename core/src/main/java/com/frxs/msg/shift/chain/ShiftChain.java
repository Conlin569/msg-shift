/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.chain;

import com.frxs.msg.shift.api.domain.EmployeeDto;
import com.frxs.msg.shift.api.domain.PickRequest;
import com.frxs.msg.shift.chain.node.LastTimeDutyRecordHandler;
import com.frxs.msg.shift.chain.node.PickOnDutyHandler;
import com.frxs.msg.shift.chain.node.ThisTimeDutyRecordHandler;
import com.frxs.msg.shift.dal.entity.Employee;
import com.frxs.msg.shift.mapstruct.EmployeeMapStruct;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ouyangzhaobing
 * @version : ShiftChain.java,v 0.1 2020年07月31日 4:21 下午
 */
@Component
public class ShiftChain {

    @Resource
    private LastTimeDutyRecordHandler lastTimeDutyRecordHandler;

    @Resource
    private PickOnDutyHandler pickOnDutyHandler;

    @Resource
    private ThisTimeDutyRecordHandler thisTimeDutyRecordHandler;

    private void initChain() {
        lastTimeDutyRecordHandler.nextHandler = pickOnDutyHandler;
        pickOnDutyHandler.nextHandler = thisTimeDutyRecordHandler;
    }

    public List<EmployeeDto> execute(PickRequest pickRequest) {
        initChain();
        PickContext pickContext = buildPickContext(pickRequest);
        List<Employee> list = new ArrayList<>();
        for (int i = 0; i <= pickRequest.getNextTimes(); i++) {
            lastTimeDutyRecordHandler.handlerRequest(pickContext);
            list.add(pickContext.getNextEmployee());
        }
//        lastTimeDutyRecordHandler.handlerRequest(pickContext);
        return EmployeeMapStruct.MAPPER.toEmployeeDtos(list);
    }

    private PickContext buildPickContext(PickRequest pickRequest) {
        PickContext pickContext = new PickContext();
        pickContext.setPickRequest(pickRequest);
        return pickContext;
    }
}