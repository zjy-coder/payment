package com.zjy.paymentdemo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@MapperScan("com.zjy.paymentdemo.mapper")
public class MybatisConfig {
}
