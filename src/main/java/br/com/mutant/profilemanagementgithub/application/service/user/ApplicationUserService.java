package br.com.mutant.profilemanagementgithub.application.service.user;

import br.com.mutant.profilemanagementgithub.domain.exceptions.user.ApplicationUserException;
import br.com.mutant.profilemanagementgithub.domain.exceptions.role.RoleException;
import br.com.mutant.profilemanagementgithub.domain.model.user.ApplicationUser;
import br.com.mutant.profilemanagementgithub.domain.model.role.Role;
import br.com.mutant.profilemanagementgithub.domain.ports.provided.user.AddRoleToUserUseCase;
import br.com.mutant.profilemanagementgithub.domain.ports.provided.user.FetchAllUsersUseCase;
import br.com.mutant.profilemanagementgithub.domain.ports.required.role.ApplicationUserRepository;
import br.com.mutant.profilemanagementgithub.domain.ports.required.user.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationUserService implements AddRoleToUserUseCase, FetchAllUsersUseCase {

    private final ApplicationUserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public void addRoleToUser(Long roleId, Long userId) {
        validateRequestObjects(roleId, userId);

        Role role = roleRepository.findById(roleId);
        ApplicationUser user = userRepository.findById(userId);

        user.addRole(role);

        userRepository.save(user);
    }

    private void validateRequestObjects(Long roleId, Long userId) {
        validateUserId(userId);
        validateRoleId(roleId);
    }

    private void validateRoleId(Long role) {
        if (role == null){
            throw RoleException.roleCannotBeNull();
        }
    }

    private void validateUserId(Long userId) {
        if (userId == null){
            throw ApplicationUserException.requiredUserId();
        }
    }

    @Override
    public List<ApplicationUser> findAllUsers() {
        return userRepository.findAll();
    }
}
