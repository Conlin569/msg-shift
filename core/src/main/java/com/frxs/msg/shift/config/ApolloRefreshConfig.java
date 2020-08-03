/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.config;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author ouyangzhaobing
 * @version : ApolloRefreshConfig.java,v 0.1 2020年07月28日 4:10 下午
 */
@Slf4j
@Getter
@Setter
@Configuration
@EnableApolloConfig({"application", "dynamicConfig"})
public class ApolloRefreshConfig {

    @Value("${show.access.log:true}")
    private boolean showAccessLog;
}