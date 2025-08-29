package br.com.mutant.profilemanagementgithub;

import org.springframework.boot.SpringApplication;

public class TestProfileManagementGithubApplication {

	public static void main(String[] args) {
		SpringApplication.from(ProfileManagementGithubApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
