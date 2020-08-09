/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.service.impl;

import com.frxs.msg.shift.dal.entity.Topic;
import com.frxs.msg.shift.dal.mapper.TopicMapper;
import com.frxs.msg.shift.service.TopicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ouyangzhaobing
 * @version : TopicServiceImpl.java,v 0.1 2020年08月04日 2:48 下午
 */
@Service
public class TopicServiceImpl implements TopicService {

    @Resource
    private TopicMapper topicMapper;

    @Override
    public Topic queryByDepartmentId(Integer departmentId) {
        return topicMapper.selectByDepartmentId(departmentId);
    }
}