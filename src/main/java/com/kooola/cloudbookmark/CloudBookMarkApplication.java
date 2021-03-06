package com.kooola.cloudbookmark;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Created by march on 2018/7/26.
 */

@ServletComponentScan
@SpringBootApplication
@MapperScan("com.kooola.cloudbookmark.dao")
public class CloudBookMarkApplication {
    public static void main(String[] args){
        SpringApplication.run(CloudBookMarkApplication.class);
    }
}
