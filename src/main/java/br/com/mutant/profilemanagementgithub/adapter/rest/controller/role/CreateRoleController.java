package br.com.mutant.profilemanagementgithub.adapter.rest.controller.role;

import br.com.mutant.profilemanagementgithub.adapter.rest.controller.role.dto.RoleCreateRequest;
import br.com.mutant.profilemanagementgithub.adapter.rest.controller.role.dto.RoleResponse;
import br.com.mutant.profilemanagementgithub.adapter.rest.controller.role.mapper.RestRoleMapper;
import br.com.mutant.profilemanagementgithub.domain.model.role.Role;
import br.com.mutant.profilemanagementgithub.domain.ports.provided.role.CreateRoleUseCase;
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
@RequestMapping("api/role")
@RequiredArgsConstructor
@Tag(name = "Roles", description = "Endpoints for managing user roles")
public class CreateRoleController {

    private final CreateRoleUseCase createRoleUseCase;

    @PostMapping
    @Operation(summary = "Create a new role",
        description = "Creates and persists a new role with the provided details.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Role created successfully.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = void.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request data.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = void.class))),
        @ApiResponse(responseCode = "409", description = "Role already exists.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = void.class)))
    })
    public ResponseEntity<RoleResponse> createNewRole(@RequestBody RoleCreateRequest request){
        Role role = RestRoleMapper.mapperToRole(request);
        role = createRoleUseCase.createNewRole(role);
        RoleResponse response = RestRoleMapper.mapperToRoleResponse(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
