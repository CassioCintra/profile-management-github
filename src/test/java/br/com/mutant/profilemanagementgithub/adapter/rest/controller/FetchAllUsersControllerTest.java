package br.com.mutant.profilemanagementgithub.adapter.rest.controller;

import br.com.mutant.profilemanagementgithub.domain.model.GitHubUser;
import br.com.mutant.profilemanagementgithub.domain.ports.provided.AddRoleToUserUseCase;
import br.com.mutant.profilemanagementgithub.domain.ports.provided.FetchAllUsersUseCase;
import br.com.mutant.profilemanagementgithub.helpers.GitHubUsersFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser
@WebMvcTest(FetchAllUsersController.class)
class FetchAllUsersControllerTest {

    @MockitoBean
    private FetchAllUsersUseCase fetchAllUsersUseCase;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void fetch_all_users_and_return_200_ok() throws Exception {
        List<GitHubUser> findUsers = GitHubUsersFactory.generateGitHubUsers(30);
        when(fetchAllUsersUseCase.findAllUsers()).thenReturn(findUsers);

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(findUsers)));
    }

    @Test
    void should_return_empty_list_and_status_200_ok() throws Exception {
        when(fetchAllUsersUseCase.findAllUsers()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Collections.emptyList())));
    }
}