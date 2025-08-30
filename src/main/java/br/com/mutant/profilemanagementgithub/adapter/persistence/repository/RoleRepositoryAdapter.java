package br.com.mutant.profilemanagementgithub.adapter.persistence.repository;

import br.com.mutant.profilemanagementgithub.adapter.persistence.entity.RoleEntity;
import br.com.mutant.profilemanagementgithub.adapter.persistence.mapper.RolesEntityMapper;
import br.com.mutant.profilemanagementgithub.domain.model.Role;
import br.com.mutant.profilemanagementgithub.domain.ports.required.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryAdapter implements RoleRepository {

    private final RoleJpaRepository repository;

    @Override
    public void save(Role role) {
        RoleEntity entity = RolesEntityMapper.mapToRoleEntity(role);
        repository.save(entity);
    }

    public Role findByName(String name){
        RoleEntity entity = repository.findByName(name);
        return RolesEntityMapper.mapToRole(entity);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
