package br.com.mutant.profilemanagementgithub.adapter.rest.controller.user;

import br.com.mutant.profilemanagementgithub.adapter.rest.controller.user.dto.UserResponse;
import br.com.mutant.profilemanagementgithub.adapter.rest.controller.user.mapper.RestUserMapper;
import br.com.mutant.profilemanagementgithub.domain.model.user.ApplicationUser;
import br.com.mutant.profilemanagementgithub.domain.ports.provided.user.FetchAllUsersUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "Endpoints for managing users")
public class FetchAllUsersController {

    private final FetchAllUsersUseCase fetchAllUsersUseCase;

    @GetMapping
    @Operation(summary = "Fetch all users",
        description = "Fetch all users persisted in the system with his rules.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Users fetched successfully",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = UserResponse.class))),
    })
    public ResponseEntity<List<UserResponse>> fetchAllUsers(){
        List<ApplicationUser> findUsers = fetchAllUsersUseCase.findAllUsers();
        List<UserResponse> usersResponse = findUsers.stream()
                .map(RestUserMapper::mapperToUserResponse)
                .toList();
        return ResponseEntity.ok().body(usersResponse);
    }
}
