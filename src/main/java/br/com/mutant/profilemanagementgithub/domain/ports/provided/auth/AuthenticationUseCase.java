package br.com.mutant.profilemanagementgithub.domain.ports.provided.auth;

import br.com.mutant.profilemanagementgithub.domain.model.user.ApplicationUser;
import br.com.mutant.profilemanagementgithub.domain.model.auth.AuthRequest;

public interface AuthenticationUseCase {

    ApplicationUser authorize(AuthRequest authRequest);
}
