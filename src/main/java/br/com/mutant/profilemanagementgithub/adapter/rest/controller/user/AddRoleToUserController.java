package br.com.mutant.profilemanagementgithub.adapter.rest.controller.user;

import br.com.mutant.profilemanagementgithub.domain.ports.provided.user.AddRoleToUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class AddRoleToUserController {

    private final AddRoleToUserUseCase addRoleToUserUseCase;

    @PostMapping
    public ResponseEntity<Void>  addRoleToUser(@RequestParam Long userId, @RequestParam Long roleId){
        addRoleToUserUseCase.addRoleToUser(userId,roleId);
        return ResponseEntity.ok().build();
    }
}
