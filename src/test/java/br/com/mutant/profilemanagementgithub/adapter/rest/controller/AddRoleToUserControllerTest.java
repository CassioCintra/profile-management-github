package br.com.mutant.profilemanagementgithub.adapter.rest.controller;

import br.com.mutant.profilemanagementgithub.domain.ports.provided.AddRoleToUserUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser
@WebMvcTest(AddRoleToUserController.class)
class AddRoleToUserControllerTest {

    @MockitoBean
    private AddRoleToUserUseCase addRoleToUserUseCase;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_add_a_new_role_to_user_and_return_200_ok() throws Exception {
        doNothing().when(addRoleToUserUseCase).addRoleToUser(any(), any());

        mockMvc.perform(
                post("/api/user")
                        .param("roleId", "1")
                        .param("userId", "1")
                        .with(csrf())
                )
                .andExpect(status().isOk());
    }
}