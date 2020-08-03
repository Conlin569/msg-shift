/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.dal.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.frxs.msg.shift.dal.entity.DutyRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * @author ouyangzhaobing
 * @version : DutyHistoryMapper.java,v 0.1 2020年07月21日 3:45 下午
 */
@Repository
public interface DutyRecordMapper extends BaseMapper<DutyRecord> {

    /**
     * 根据部门id查询该部门的正常值班记录，时间倒序
     *
     * @param departmentId
     * @return
     */
    List<DutyRecord> selectByDepartmentId(Integer departmentId);


    /**
     * 根据部门id, 值班日期和值班员工查询
     *
     * @param dutyRecord
     * @return
     */
    DutyRecord selectByDeptIdAndDutyDateAndEmpId(@Param("param") DutyRecord dutyRecord);

    DutyRecord selectRecentByDepartmentId(Integer departmentId);

    DutyRecord selectTodayOnDuty(@Param("departmentId") Integer departmentId, @Param("date") LocalDate date);
}