/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.facade.impl;

import com.frxs.msg.shift.api.domain.EmployeeDto;
import com.frxs.msg.shift.api.domain.PickRequest;
import com.frxs.msg.shift.api.exception.BizException;
import com.frxs.msg.shift.api.facade.ShiftFacade;
import com.frxs.msg.shift.api.result.ResultTO;
import com.frxs.msg.shift.api.result.ResultUtil;
import com.frxs.msg.shift.chain.ShiftChain;
import com.frxs.msg.shift.manager.AbstractShift;
import com.frxs.msg.shift.manager.ShiftManager;
import com.frxs.msg.shift.service.DepartmentService;
import com.frxs.msg.shift.service.ShiftService;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

import java.util.List;
import java.util.Objects;

import static com.frxs.msg.shift.api.exception.enums.ErrorCodeDetailEnum.PARAM_ERROR;

/**
 * @author ouyangzhaobing
 * @version : ShiftFacadeImpl.java,v 0.1 2020年07月22日 10:11 上午
 */
@Service(version = "1.0.0")
public class ShiftFacadeImpl implements ShiftFacade {

    @Resource
    private ShiftService shiftService;

    @Resource
    private DepartmentService departmentService;

    @Resource
    private ShiftChain shiftChain;

    @Resource
    private AbstractShift<EmployeeDto, PickRequest> abstractShift;

    @Override
    public ResultTO<EmployeeDto> queryOnDuty(Integer departmentId, Integer times, Integer cycle) {
        if (times < 0) {
            throw new BizException(PARAM_ERROR, "天数不能小于0！");
        }
        if (Objects.isNull(departmentId)) {
            throw new BizException(PARAM_ERROR, "部门id不能为空！");
        }
        if (Objects.isNull(departmentService.queryById(departmentId))) {
            throw new BizException(PARAM_ERROR, "部门id不存在！");
        }
        return ResultUtil.success(shiftService.queryOnDuty(departmentId, times, cycle));
    }

    @Override
    public ResultTO<List<EmployeeDto>> pickOnDuty(PickRequest pickRequest) {
        return ResultUtil.success(shiftChain.execute(pickRequest));
    }

    @Override
    public ResultTO<EmployeeDto> pickOne(PickRequest pickRequest) {
        return ResultUtil.success(abstractShift.generateDutyMan(pickRequest));
    }
}