package br.com.mutant.profilemanagementgithub.adapter.rest.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "github-api", url = "https://api.github.com")
public interface GitHubClient {

    @GetMapping("/users")
    List<GitHubUserResponse> getUsers(@RequestParam(name = "per_page") Integer quantity);
}
