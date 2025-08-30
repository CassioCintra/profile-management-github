package br.com.mutant.profilemanagementgithub.application.service;

import br.com.mutant.profilemanagementgithub.domain.exceptions.RoleException;
import br.com.mutant.profilemanagementgithub.domain.model.Role;
import br.com.mutant.profilemanagementgithub.domain.ports.provided.CreateRoleUseCase;
import br.com.mutant.profilemanagementgithub.domain.ports.required.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService implements CreateRoleUseCase {

    private final RoleRepository roleRepository;

    @Override
    public void createNewRole(Role role) {
        validateRole(role);
        roleRepository.save(role);
    }

    private void validateRole(Role role) {
        validateIfRoleIsNull(role);
        validateIfRoleAlreadyExists(role);
    }

    private void validateIfRoleIsNull(Role role) {
        if (role == null || role.getName() == null){
            throw RoleException.roleCannotBeNull();
        }
    }

    private void validateIfRoleAlreadyExists(Role role) {
        if (roleRepository.existsByName(role.getName())){
            throw RoleException.roleAlreadyExists(role.getName());
        }
    }
}
