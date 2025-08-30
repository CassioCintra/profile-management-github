package br.com.mutant.profilemanagementgithub.adapter.persistence.repository;

import br.com.mutant.profilemanagementgithub.adapter.persistence.entity.GitHubUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GitHubUserJpaRepository extends JpaRepository<GitHubUserEntity, Long> {

}
