package br.com.mutant.profilemanagementgithub.application.service;

import br.com.mutant.profilemanagementgithub.domain.exceptions.GitHubUserException;
import br.com.mutant.profilemanagementgithub.domain.exceptions.RoleException;
import br.com.mutant.profilemanagementgithub.domain.model.GitHubUser;
import br.com.mutant.profilemanagementgithub.domain.model.Role;
import br.com.mutant.profilemanagementgithub.domain.ports.provided.AddRoleToUserUseCase;
import br.com.mutant.profilemanagementgithub.domain.ports.provided.FetchAllUsersUseCase;
import br.com.mutant.profilemanagementgithub.domain.ports.required.GitHubUserRepository;
import br.com.mutant.profilemanagementgithub.domain.ports.required.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GitHubUserService implements AddRoleToUserUseCase, FetchAllUsersUseCase {

    private final GitHubUserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public void addRoleToUser(Long roleId, Long userId) {
        validateRequestObjects(roleId, userId);

        Role role = roleRepository.findById(roleId);
        GitHubUser user = userRepository.findById(userId);

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
            throw GitHubUserException.requiredUserId();
        }
    }

    @Override
    public List<GitHubUser> findAllUsers() {
        return userRepository.findAll();
    }
}
