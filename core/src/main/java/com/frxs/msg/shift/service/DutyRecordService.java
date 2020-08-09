/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.service;

import com.frxs.msg.shift.dal.entity.DutyRecord;

import java.time.LocalDate;
import java.util.List;

/**
 * @author ouyangzhaobing
 * @version : DutyHistoryService.java,v 0.1 2020年07月22日 10:09 上午
 */
public interface DutyRecordService {

    /**
     * 根据部门id查询该部门的值班记录,时间倒序排列
     *
     * @param departmentId
     * @return
     */
    List<DutyRecord> queryByDepartmentId(Integer departmentId);

    /**
     * 保存值班记录
     *
     * @param dutyRecord
     */
    void save(DutyRecord dutyRecord);

    /**
     * 查询该部门最近的一条值班记录
     *
     * @param departmentId
     * @return
     */
    DutyRecord queryRecentByDepartmentId(Integer departmentId);

    /**
     * 查询指定日期的值班记录
     *
     * @param departmentId
     * @param date
     * @return
     */
    DutyRecord queryByDepartmentIdAndDate(Integer departmentId, LocalDate date);
}