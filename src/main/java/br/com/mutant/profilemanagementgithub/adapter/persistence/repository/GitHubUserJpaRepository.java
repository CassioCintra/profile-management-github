package br.com.mutant.profilemanagementgithub.adapter.persistence.repository;

import br.com.mutant.profilemanagementgithub.adapter.persistence.entity.GitHubUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GitHubUserJpaRepository extends JpaRepository<GitHubUserEntity, Long> {

    @Query("""
        SELECT user
        FROM GitHubUserEntity user
        LEFT JOIN FETCH user.roles
        ORDER BY user.id
    """)
    List<GitHubUserEntity> findAll();
}
