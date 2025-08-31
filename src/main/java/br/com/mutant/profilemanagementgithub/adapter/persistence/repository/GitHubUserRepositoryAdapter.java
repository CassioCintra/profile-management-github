package br.com.mutant.profilemanagementgithub.adapter.persistence.repository;

import br.com.mutant.profilemanagementgithub.adapter.persistence.entity.GitHubUserEntity;
import br.com.mutant.profilemanagementgithub.adapter.persistence.mapper.GitHubEntityMapper;
import br.com.mutant.profilemanagementgithub.domain.exceptions.GitHubUserException;
import br.com.mutant.profilemanagementgithub.domain.model.GitHubUser;
import br.com.mutant.profilemanagementgithub.domain.ports.required.GitHubUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GitHubUserRepositoryAdapter implements GitHubUserRepository {

    private final GitHubUserJpaRepository gitHubUserJpaRepository;

    @Override
    public void save(GitHubUser user) {
        GitHubUserEntity entity = GitHubEntityMapper.mapToGitHubUserEntity(user);
        gitHubUserJpaRepository.save(entity);
    }

    @Override
    public void saveAll(List<GitHubUser> users) {
        List<GitHubUserEntity> userEntityList = users.stream()
                .map(GitHubEntityMapper::mapToGitHubUserEntity)
                .toList();
        gitHubUserJpaRepository.saveAll(userEntityList);
    }

    @Override
    public void deleteAll() {
        gitHubUserJpaRepository.deleteAll();
    }

    @Override
    public List<GitHubUser> findAll() {
        List<GitHubUserEntity> entities = gitHubUserJpaRepository.findAll();
        return entities.stream()
                .map(GitHubEntityMapper::mapToGitHubUser)
                .toList();
    }

    @Override
    public GitHubUser findById(Long userId) {
        return gitHubUserJpaRepository.findById(userId)
                .map(GitHubEntityMapper::mapToGitHubUser)
                .orElseThrow(GitHubUserException::cannotFindUser);
    }
}
