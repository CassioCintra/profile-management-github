package br.com.mutant.profilemanagementgithub.adapter.rest.controller.user;

import br.com.mutant.profilemanagementgithub.adapter.rest.controller.user.dto.UserCreateRequest;
import br.com.mutant.profilemanagementgithub.adapter.rest.controller.user.dto.UserResponse;
import br.com.mutant.profilemanagementgithub.adapter.rest.controller.user.mapper.RestUserMapper;
import br.com.mutant.profilemanagementgithub.domain.model.user.ApplicationUser;
import br.com.mutant.profilemanagementgithub.domain.ports.provided.user.CreateUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "Endpoints for managing users")
public class CreateUserController {

    private final CreateUserUseCase createUserUseCase;

    @PostMapping
    @Operation(summary = "Create a new user",
        description = "Creates and persists a new user with the provided details.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "User created successfully.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = UserResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request data.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = void.class))),
        @ApiResponse(responseCode = "409", description = "User with this login already exists.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = void.class)))
    })
    public ResponseEntity<UserResponse> createUser(@RequestBody UserCreateRequest request){
        ApplicationUser user = RestUserMapper.mapperToUser(request);
        user = createUserUseCase.create(user);
        UserResponse response = RestUserMapper.mapperToUserResponse(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
