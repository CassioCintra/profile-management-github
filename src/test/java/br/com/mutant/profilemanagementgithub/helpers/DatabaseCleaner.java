package br.com.mutant.profilemanagementgithub.helpers;

import br.com.mutant.profilemanagementgithub.adapter.persistence.repository.user.ApplicationUserJpaRepository;
import br.com.mutant.profilemanagementgithub.adapter.persistence.repository.role.RoleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseCleaner {

    private final ApplicationUserJpaRepository userJpaRepository;
    private final RoleJpaRepository roleJpaRepository;

    public void cleanUp(){
        userJpaRepository.deleteAll();
        roleJpaRepository.deleteAll();
    }
}
