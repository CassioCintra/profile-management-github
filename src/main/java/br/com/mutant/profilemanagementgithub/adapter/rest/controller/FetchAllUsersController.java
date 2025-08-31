package br.com.mutant.profilemanagementgithub.adapter.rest.controller;

import br.com.mutant.profilemanagementgithub.domain.model.GitHubUser;
import br.com.mutant.profilemanagementgithub.domain.ports.provided.FetchAllUsersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class FetchAllUsersController {

    private final FetchAllUsersUseCase fetchAllUsersUseCase;

    @GetMapping
    public ResponseEntity<List<GitHubUser>> fetchAllUsers(){
        List<GitHubUser> findUsers = fetchAllUsersUseCase.findAllUsers();
        return ResponseEntity.ok().body(findUsers);
    }
}
