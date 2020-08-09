/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.service;

import com.frxs.msg.shift.dal.entity.Employee;

/**
 * @author ouyangzhaobing
 * @version : DutyManService.java,v 0.1 2020年07月22日 10:07 上午
 */
public interface EmployeeService {

    Employee queryById(Integer id);
}