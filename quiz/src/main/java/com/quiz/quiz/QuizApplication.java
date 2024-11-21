package com.quiz.quiz;

import com.quiz.quiz.config.audit.SpringSecurityAuditorAware;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
@ComponentScan(basePackages = {
		"org.springframework.boot.context.embedded.tomcat",
		"com.quiz.quiz"
})
@EntityScan(basePackages = "com.quiz.quiz")
@EnableJpaRepositories(basePackages = "com.quiz.quiz")
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableAsync
public class QuizApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(QuizApplication.class, args);
	}
	@Bean
	public AuditorAware<String> auditorAware() {
		return new SpringSecurityAuditorAware();
	}

}
