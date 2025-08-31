package br.com.mutant.profilemanagementgithub.domain.ports.provided.user;

public interface AddRoleToUserUseCase {

    void addRoleToUser(Long roleId, Long userId);

}
