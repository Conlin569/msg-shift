/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.mapstruct;

import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.frxs.msg.shift.api.domain.EmployeeDto;
import com.frxs.msg.shift.dal.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author ouyangzhaobing
 * @version : EmployeeMapStruct.java,v 0.1 2020年07月28日 5:12 下午
 */
@Mapper
public interface EmployeeMapStruct {

    EmployeeMapStruct MAPPER = Mappers.getMapper(EmployeeMapStruct.class);

    EmployeeDto toEmployeeDto(Employee Employee);

    default List<EmployeeDto> toEmployeeDtos(List<Employee> Employees) {
        List<EmployeeDto> list = Collections.emptyList();
        if (CollectionUtils.isNotEmpty(Employees)) {
            list = new ArrayList<>(Employees.size());
            for (Employee employee : Employees) {
                list.add(toEmployeeDto(employee));
            }
        }
        return list;
    }
}