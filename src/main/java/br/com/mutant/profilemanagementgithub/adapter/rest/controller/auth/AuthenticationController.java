package br.com.mutant.profilemanagementgithub.adapter.rest.controller.auth;

import br.com.mutant.profilemanagementgithub.adapter.rest.controller.auth.dto.JwtResponse;
import br.com.mutant.profilemanagementgithub.adapter.rest.controller.auth.dto.LoginRequest;
import br.com.mutant.profilemanagementgithub.adapter.security.JwtTokenUtils;
import br.com.mutant.profilemanagementgithub.domain.model.user.ApplicationUser;
import br.com.mutant.profilemanagementgithub.domain.model.auth.AuthRequest;
import br.com.mutant.profilemanagementgithub.domain.ports.provided.auth.AuthenticationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationUseCase authenticationUseCase;
    private final JwtTokenUtils jwtTokenUtils;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest){

        ApplicationUser user = authenticationUseCase.authorize(
                new AuthRequest(loginRequest.login(), loginRequest.password()));

        String token = jwtTokenUtils.generateToken(user.getLogin());

        return ResponseEntity.ok(new JwtResponse(token));
    }
}
