package br.com.mutant.profilemanagementgithub.domain.ports.required.user;

import br.com.mutant.profilemanagementgithub.domain.model.user.ApplicationUser;

import java.util.List;

public interface ApplicationUserRepository {

    ApplicationUser save(ApplicationUser user);
    void saveAll(List<ApplicationUser> users);
    void deleteAll();

    List<ApplicationUser> findAll();

    ApplicationUser findById(Long userId);

    ApplicationUser findByLogin(String login);

    boolean existsByLogin(String login);
}
