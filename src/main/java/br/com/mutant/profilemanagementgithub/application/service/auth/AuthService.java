package br.com.mutant.profilemanagementgithub.application.service.auth;

import br.com.mutant.profilemanagementgithub.domain.model.GitHubUser;
import br.com.mutant.profilemanagementgithub.domain.model.auth.AuthRequest;
import br.com.mutant.profilemanagementgithub.domain.ports.provided.auth.AuthenticationUseCase;
import br.com.mutant.profilemanagementgithub.domain.ports.required.GitHubUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService implements AuthenticationUseCase {

    private final GitHubUserRepository userRepository;

    @Override
    public GitHubUser authorize(AuthRequest authRequest) {
        GitHubUser user = userRepository.findByLogin(authRequest.getLogin());

        user.validatePassword(authRequest.getPassword());

        return user;
    }
}
