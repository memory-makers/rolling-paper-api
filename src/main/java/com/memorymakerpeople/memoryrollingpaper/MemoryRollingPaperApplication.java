package com.memorymakerpeople.memoryrollingpaper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MemoryRollingPaperApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemoryRollingPaperApplication.class, args);
	}

	//아예 지우면 오쳥은 오는데 받지를 못함.
/*	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurers {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				//registry.addMapping("/**").allowedOrigins("http://front-server.com");//요청도 안옴
				registry.addMapping("/**")
						.allowedMethods("*")//추가 안하면 put 등은 허용안됨.
						//.allowedOrigins("*");//전부 다 받을 수있음.
						.allowedOrigins("https://rolling-paper-client-blue.vercel.app");
			}
		};
	}*/
}
