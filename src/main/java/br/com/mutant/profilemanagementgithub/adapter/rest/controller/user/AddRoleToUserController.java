package br.com.mutant.profilemanagementgithub.adapter.rest.controller.user;

import br.com.mutant.profilemanagementgithub.adapter.rest.controller.user.dto.UserResponse;
import br.com.mutant.profilemanagementgithub.domain.ports.provided.user.AddRoleToUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "Endpoints for managing users")
public class AddRoleToUserController {

    private final AddRoleToUserUseCase addRoleToUserUseCase;

    @PostMapping("{userId}/roles")
    @Operation(summary = "Create a new user",
        description = "Creates and persists a new user with the provided details.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Role added successfully.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = void.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request data.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = void.class))),
        @ApiResponse(responseCode = "409", description = "User already has role.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = void.class)))
    })
    public ResponseEntity<Void>  addRoleToUser(@PathVariable Long userId, @RequestParam Long roleId){
        log.info("received params: userId = {}, roleId = {}", userId, roleId);
        addRoleToUserUseCase.addRoleToUser(roleId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
