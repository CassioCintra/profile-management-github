package br.com.mutant.profilemanagementgithub.application.runner;

import br.com.mutant.profilemanagementgithub.domain.model.user.ApplicationUser;
import br.com.mutant.profilemanagementgithub.domain.ports.required.githubapi.GitHubApiPort;
import br.com.mutant.profilemanagementgithub.domain.ports.required.user.ApplicationUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@Profile("!test")
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final GitHubApiPort gitHubApiPort;
    private final ApplicationUserRepository applicationUserRepository;

    private static final Integer USERS_QUANTITY = 30;

    @Override
    public void run(String... args) {
        log.info("Starting database seeder...");
        try {
            List<ApplicationUser> userList = gitHubApiPort.getGitHubUsers(USERS_QUANTITY);
            if (userList == null || userList.isEmpty()) {
                log.info("No GitHub users found to save. Seeder finished successfully.");
                return;
            }
            persistFoundUsers(userList);
        } catch (Exception e) {
            log.error("Database seeder failed to complete! Cause: {}", e.getMessage(), e);
        } finally {
            log.info("Database seeder finished.");
        }
    }

    private void persistFoundUsers(List<ApplicationUser> userList) {
        log.info("Found {} GitHub users to save. Starting persistence...", userList.size());
        applicationUserRepository.deleteAll();
        applicationUserRepository.saveAll(userList);
        log.info("GitHub users saved successfully!");
    }
}
