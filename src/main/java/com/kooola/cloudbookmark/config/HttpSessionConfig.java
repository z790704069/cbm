package com.kooola.cloudbookmark.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by march on 2018/7/28.
 * Http Session with redis
 */

@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {
}
