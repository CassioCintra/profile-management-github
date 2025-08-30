package br.com.mutant.profilemanagementgithub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ProfileManagementGithubApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfileManagementGithubApplication.class, args);
	}

}
