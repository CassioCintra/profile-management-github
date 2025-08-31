package br.com.mutant.profilemanagementgithub.adapter.persistence.repository.user;

import br.com.mutant.profilemanagementgithub.adapter.persistence.entity.user.ApplicationUserEntity;
import br.com.mutant.profilemanagementgithub.adapter.persistence.mapper.ApplicationUserEntityMapper;
import br.com.mutant.profilemanagementgithub.domain.exceptions.user.UserNotFoundException;
import br.com.mutant.profilemanagementgithub.domain.model.user.ApplicationUser;
import br.com.mutant.profilemanagementgithub.domain.ports.required.user.ApplicationUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ApplicationUserRepositoryAdapter implements ApplicationUserRepository {

    private final ApplicationUserJpaRepository applicationUserJpaRepository;

    @Override
    public ApplicationUser save(ApplicationUser user) {
        ApplicationUserEntity entity = ApplicationUserEntityMapper.mapToGitHubUserEntity(user);
        entity = applicationUserJpaRepository.save(entity);
        return ApplicationUserEntityMapper.mapToGitHubUser(entity);
    }

    @Override
    public void saveAll(List<ApplicationUser> users) {
        List<ApplicationUserEntity> userEntityList = users.stream()
                .map(ApplicationUserEntityMapper::mapToGitHubUserEntity)
                .toList();
        applicationUserJpaRepository.saveAll(userEntityList);
    }

    @Override
    public void deleteAll() {
        applicationUserJpaRepository.deleteAll();
    }

    @Override
    public List<ApplicationUser> findAll() {
        List<ApplicationUserEntity> entities = applicationUserJpaRepository.findAll();
        return entities.stream()
                .map(ApplicationUserEntityMapper::mapToGitHubUser)
                .toList();
    }

    @Override
    public ApplicationUser findById(Long userId) {
        return applicationUserJpaRepository.findById(userId)
                .map(ApplicationUserEntityMapper::mapToGitHubUser)
                .orElseThrow(UserNotFoundException::notFoundById);
    }

    @Override
    public ApplicationUser findByLogin(String login) {
        return applicationUserJpaRepository.findByLogin(login)
                .map(ApplicationUserEntityMapper::mapToGitHubUser)
                .orElseThrow(UserNotFoundException::notFoundByLogin);
    }

    @Override
    public boolean existsByLogin(String login) {
        return applicationUserJpaRepository.existsByLogin(login);
    }
}
