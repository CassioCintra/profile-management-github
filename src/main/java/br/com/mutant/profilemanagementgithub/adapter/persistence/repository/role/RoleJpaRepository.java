package br.com.mutant.profilemanagementgithub.adapter.persistence.repository.role;

import br.com.mutant.profilemanagementgithub.adapter.persistence.entity.role.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleJpaRepository extends JpaRepository<RoleEntity, Long> {

    @Query("""
        SELECT role
        FROM RoleEntity role
        WHERE role.name = :name
    """)
    RoleEntity findByName(String name);

    @Query("""
        SELECT COUNT(role) > 0
        FROM RoleEntity role
        WHERE role.name = :name
    """)
    Boolean existsByName(String name);
}
