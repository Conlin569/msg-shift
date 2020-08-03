/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.dal.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.frxs.msg.shift.dal.entity.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author ouyangzhaobing
 * @version : DutyManMapper.java,v 0.1 2020年07月21日 3:45 下午
 */
@Repository
public interface EmployeeMapper extends BaseMapper<Employee> {
    /**
     * 查询该部门下第一个被录入的员工
     *
     * @param departmentId
     * @return
     */
    Employee selectFirstByDepartmentId(Integer departmentId);

    /**
     * 查询该部门下该员工的下一位员工
     *
     * @param departmentId
     * @param employeeId
     * @return
     */
    Employee selectNextById(@Param("departmentId") Integer departmentId, @Param("employeeId") Integer employeeId);
}