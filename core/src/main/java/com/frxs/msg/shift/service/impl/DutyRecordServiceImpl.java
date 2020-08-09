/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.service.impl;

import com.frxs.msg.shift.dal.entity.DutyRecord;
import com.frxs.msg.shift.dal.mapper.DutyRecordMapper;
import com.frxs.msg.shift.service.DutyRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

/**
 * @author ouyangzhaobing
 * @version : DutyRecordServiceImpl.java,v 0.1 2020年07月28日 5:18 下午
 */
@Service
public class DutyRecordServiceImpl implements DutyRecordService {

    @Resource
    private DutyRecordMapper dutyRecordMapper;


    @Override
    public List<DutyRecord> queryByDepartmentId(Integer departmentId) {
        return dutyRecordMapper.selectByDepartmentId(departmentId);
    }

    @Override
    public void save(DutyRecord dutyRecord) {
        dutyRecordMapper.insert(dutyRecord);

    }

    @Override
    public DutyRecord queryRecentByDepartmentId(Integer departmentId) {
        return dutyRecordMapper.selectRecentByDepartmentId(departmentId);
    }

    @Override
    public DutyRecord queryByDepartmentIdAndDate(Integer departmentId, LocalDate date) {
        return dutyRecordMapper.selectByDepartmentIdAndDate(departmentId, date);
    }
}