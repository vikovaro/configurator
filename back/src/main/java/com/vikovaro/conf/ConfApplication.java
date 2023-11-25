package com.vikovaro.conf;

import com.vikovaro.conf.utilities.Load;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ConfApplication {

	public static void main(String[] args) {
		Load.startup();
		SpringApplication.run(ConfApplication.class, args);
	}

}
