package br.com.mutant.profilemanagementgithub.adapter.persistence.repository.user;

import br.com.mutant.profilemanagementgithub.adapter.persistence.entity.user.ApplicationUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ApplicationUserJpaRepository extends JpaRepository<ApplicationUserEntity, Long> {

    @Query("""
        SELECT user
        FROM ApplicationUserEntity user
        LEFT JOIN FETCH user.roles
        ORDER BY user.id
    """)
    List<ApplicationUserEntity> findAll();

    Optional<ApplicationUserEntity> findByLogin(String login);

    @Query("""
        SELECT COUNT(user) > 0
        FROM ApplicationUserEntity user
        WHERE user.login = :login
    """)
    boolean existsByLogin(String login);
}
