package com.wang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author wangguangpeng
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan(basePackages = {"com.wang.mybatisplus.generator.**.mapper"})
public class GeneratorApplication {
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
    }
}
