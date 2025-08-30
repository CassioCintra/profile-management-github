package br.com.mutant.profilemanagementgithub.adapter.persistence.repository;

import br.com.mutant.profilemanagementgithub.adapter.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleJpaRepository extends JpaRepository<RoleEntity, Long> {

    @Query("""
        SELECT role
        FROM RoleEntity role
        WHERE role.name = :name
    """)
    RoleEntity findByName(String name);
}
