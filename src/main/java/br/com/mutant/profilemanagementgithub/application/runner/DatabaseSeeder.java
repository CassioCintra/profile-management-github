package br.com.mutant.profilemanagementgithub.application.runner;

import br.com.mutant.profilemanagementgithub.domain.model.GitHubUser;
import br.com.mutant.profilemanagementgithub.domain.ports.required.GitHubPort;
import br.com.mutant.profilemanagementgithub.domain.ports.required.GitHubUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final GitHubPort gitHubPort;
    private final GitHubUserRepository gitHubUserRepository;

    private static final Integer USERS_QUANTITY = 30;

    @Override
    public void run(String... args) {
        log.info("Running InitialDataRunner ...");
        List<GitHubUser> userList = gitHubPort.getGitHubUsers(USERS_QUANTITY);
        if (!userList.isEmpty()){
            gitHubUserRepository.deleteAll();
            gitHubUserRepository.saveAll(userList);
            log.info("GitHub users saved successfully");
        }
        log.info("Running InitialDataRunner finished");
    }
}
