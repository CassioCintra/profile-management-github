package br.com.mutant.profilemanagementgithub.adapter.rest.controller;

import br.com.mutant.profilemanagementgithub.domain.model.Role;
import br.com.mutant.profilemanagementgithub.domain.ports.provided.CreateRoleUseCase;
import lombok.RequiredArgsConstructor;
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

    // TODO criar dtos de request e response
    @PostMapping
    public ResponseEntity<Void> createNewRole(@RequestBody Role role){
        createRoleUseCase.createNewRole(role);
        return ResponseEntity.ok().build();
    }
}
