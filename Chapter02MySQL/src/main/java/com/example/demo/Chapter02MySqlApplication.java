package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@ComponentScan(basePackages = {"user.controller", "user.service", "user.service.impl", "user.dao"})  //"user.*"  이렇게 작성하면 user로 시작하는 모든 것을 빈 설정한 것과 같다.

@MapperScan("user.dao") //스프링에서는 src/main/java/spring/SpringConfiguration에서 설정했지만 boot에서는 메인에 작성해주면 된다!!!
@EnableTransactionManagement

@SpringBootApplication  //모든 의존 관계 체크
//db의존성을 주입해 놓았는데 막상 지금 DB를 사용하지 않을 경우 괄호 안의 (exclude = DataSourceAutoConfiguration.class)를 작성해준다.

public class Chapter02MySqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chapter02MySqlApplication.class, args);
	}
	
	
	public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
	    return new HiddenHttpMethodFilter(); //deleteMapping을 사용하기 위한 코드
	}
}
