package br.com.mutant.profilemanagementgithub.adapter.rest.controller.role;

import br.com.mutant.profilemanagementgithub.adapter.rest.controller.role.dto.RoleCreateRequest;
import br.com.mutant.profilemanagementgithub.adapter.rest.controller.role.dto.RoleResponse;
import br.com.mutant.profilemanagementgithub.adapter.rest.controller.role.mapper.RestRoleMapper;
import br.com.mutant.profilemanagementgithub.domain.model.role.Role;
import br.com.mutant.profilemanagementgithub.domain.ports.provided.role.CreateRoleUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/role")
@RequiredArgsConstructor
public class CreateRoleController {

    private final CreateRoleUseCase createRoleUseCase;

    @PostMapping
    public ResponseEntity<RoleResponse> createNewRole(@RequestBody RoleCreateRequest request){
        Role role = RestRoleMapper.mapperToRole(request);
        role = createRoleUseCase.createNewRole(role);
        RoleResponse response = RestRoleMapper.mapperToRoleResponse(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
