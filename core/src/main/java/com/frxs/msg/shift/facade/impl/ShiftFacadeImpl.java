/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.facade.impl;

import com.frxs.msg.shift.api.domain.EmployeeDto;
import com.frxs.msg.shift.api.domain.PickRequest;
import com.frxs.msg.shift.api.exception.BizException;
import com.frxs.msg.shift.api.exception.enums.ErrorCodeDetailEnum;
import com.frxs.msg.shift.api.facade.ShiftFacade;
import com.frxs.msg.shift.api.result.ResultTO;
import com.frxs.msg.shift.api.result.ResultUtil;
import com.frxs.msg.shift.service.ShiftService;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.Objects;


/**
 * @author ouyangzhaobing
 * @version : ShiftFacadeImpl.java,v 0.1 2020年07月22日 10:11 上午
 */
@Service(version = "1.0.0")
public class ShiftFacadeImpl implements ShiftFacade {

    @Resource
    private ShiftService shiftService;

    @Override
    public ResultTO<EmployeeDto> queryNthTimeOnDuty(PickRequest pickRequest) {
        if (Objects.isNull(pickRequest) ||
                Objects.isNull(pickRequest.getDepartmentId()) ||
                Objects.isNull(pickRequest.getCycle()) ||
                Objects.isNull(pickRequest.getNextTimes())) {
            throw new BizException(ErrorCodeDetailEnum.PARAM_ERROR, "参数为空!");
        }
        return ResultUtil.success(shiftService.queryNthTimeOnDuty(pickRequest));
    }
}