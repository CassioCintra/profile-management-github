package br.com.mutant.profilemanagementgithub.application.service.user;

import br.com.mutant.profilemanagementgithub.domain.exceptions.role.RoleValidationException;
import br.com.mutant.profilemanagementgithub.domain.exceptions.user.InvalidUserDataException;
import br.com.mutant.profilemanagementgithub.domain.exceptions.user.UserAlreadyExistsException;
import br.com.mutant.profilemanagementgithub.domain.model.user.ApplicationUser;
import br.com.mutant.profilemanagementgithub.domain.model.role.Role;
import br.com.mutant.profilemanagementgithub.domain.ports.provided.user.AddRoleToUserUseCase;
import br.com.mutant.profilemanagementgithub.domain.ports.provided.user.CreateUserUseCase;
import br.com.mutant.profilemanagementgithub.domain.ports.provided.user.FetchAllUsersUseCase;
import br.com.mutant.profilemanagementgithub.domain.ports.required.user.ApplicationUserRepository;
import br.com.mutant.profilemanagementgithub.domain.ports.required.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationUserService implements AddRoleToUserUseCase, FetchAllUsersUseCase, CreateUserUseCase {

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
            throw RoleValidationException.roleCannotBeNull();
        }
    }

    private void validateUserId(Long userId) {
        if (userId == null){
            throw InvalidUserDataException.userIdNull();
        }
    }

    @Override
    public List<ApplicationUser> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public ApplicationUser create(ApplicationUser applicationUser) {
        validateApplicationUser(applicationUser);
        return userRepository.save(applicationUser);
    }

    private void validateApplicationUser(ApplicationUser applicationUser) {
        validateIfUserIsNull(applicationUser);
        validateIfUserLoginAlreadyExists(applicationUser);
    }

    private void validateIfUserLoginAlreadyExists(ApplicationUser applicationUser) {
        if (userRepository.existsByLogin(applicationUser.getLogin())){
            throw UserAlreadyExistsException.loginAlreadyExists();
        }
    }

    private static void validateIfUserIsNull(ApplicationUser applicationUser) {
        if (applicationUser == null){
            throw InvalidUserDataException.userNull();
        }
    }
}
