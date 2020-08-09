/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.service;

import com.frxs.msg.shift.dal.entity.Topic;

/**
 * @author ouyangzhaobing
 * @version : TopicService.java,v 0.1 2020年07月22日 10:10 上午
 */
public interface TopicService {

    /**
     * 根据部门id查询topic
     * @param departmentId
     * @return
     */
    Topic queryByDepartmentId(Integer departmentId);
}