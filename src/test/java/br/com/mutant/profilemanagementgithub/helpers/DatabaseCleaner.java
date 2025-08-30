package br.com.mutant.profilemanagementgithub.helpers;

import br.com.mutant.profilemanagementgithub.adapter.persistence.repository.GitHubUserJpaRepository;
import br.com.mutant.profilemanagementgithub.adapter.persistence.repository.RoleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseCleaner {

    private final GitHubUserJpaRepository userJpaRepository;
    private final RoleJpaRepository roleJpaRepository;

    public void cleanUp(){
        userJpaRepository.deleteAll();
        roleJpaRepository.deleteAll();
    }
}
