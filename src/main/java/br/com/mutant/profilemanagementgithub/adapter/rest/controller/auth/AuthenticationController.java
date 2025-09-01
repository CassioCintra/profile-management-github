package br.com.mutant.profilemanagementgithub.adapter.rest.controller.auth;

import br.com.mutant.profilemanagementgithub.adapter.rest.controller.auth.dto.JwtResponse;
import br.com.mutant.profilemanagementgithub.adapter.rest.controller.auth.dto.LoginRequest;
import br.com.mutant.profilemanagementgithub.adapter.security.JwtTokenUtils;
import br.com.mutant.profilemanagementgithub.domain.model.user.ApplicationUser;
import br.com.mutant.profilemanagementgithub.domain.model.auth.AuthRequest;
import br.com.mutant.profilemanagementgithub.domain.ports.provided.auth.AuthenticationUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for user authentication and login")
public class AuthenticationController {

    private final AuthenticationUseCase authenticationUseCase;
    private final JwtTokenUtils jwtTokenUtils;

    @PostMapping("/login")
    @Operation(summary = "User login",
        description = "Authenticates a user and returns a JWT token for future requests.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successfully authenticated.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = JwtResponse.class))),
        @ApiResponse(responseCode = "401", description = "Invalid credentials.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = void.class)))
    })
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest){

        ApplicationUser user = authenticationUseCase.authorize(
                new AuthRequest(loginRequest.login(), loginRequest.password()));

        String token = jwtTokenUtils.generateToken(user.getLogin());

        return ResponseEntity.ok(new JwtResponse(token));
    }
}
