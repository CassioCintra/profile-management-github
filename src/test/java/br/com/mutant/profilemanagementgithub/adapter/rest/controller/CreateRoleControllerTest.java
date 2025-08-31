package br.com.mutant.profilemanagementgithub.adapter.rest.controller;

import br.com.mutant.profilemanagementgithub.config.ControllerUnitTest;
import br.com.mutant.profilemanagementgithub.domain.model.Role;
import br.com.mutant.profilemanagementgithub.domain.ports.provided.CreateRoleUseCase;
import br.com.mutant.profilemanagementgithub.helpers.RolesFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.doNothing;

@WithMockUser
@ControllerUnitTest(controllers = CreateRoleController.class)
class CreateRoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CreateRoleUseCase createRoleUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_create_new_role_and_return_200_ok() throws Exception {
        Role newRole = RolesFactory.generateRole("NewRole");

        doNothing().when(createRoleUseCase).createNewRole(newRole);

        mockMvc.perform(post("/api/role")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newRole))
                        .with(csrf()))
                .andExpect(status().isOk());
    }
}