package br.com.mutant.profilemanagementgithub.application.service.role;

import br.com.mutant.profilemanagementgithub.domain.exceptions.role.RoleException;
import br.com.mutant.profilemanagementgithub.domain.model.role.Role;
import br.com.mutant.profilemanagementgithub.domain.ports.provided.role.CreateRoleUseCase;
import br.com.mutant.profilemanagementgithub.domain.ports.required.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService implements CreateRoleUseCase {

    private final RoleRepository roleRepository;

    @Override
    public Role createNewRole(Role role) {
        validateRole(role);
        role = roleRepository.save(role);
        return role;
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
