package br.com.mutant.profilemanagementgithub.adapter.rest.controller.role;

import br.com.mutant.profilemanagementgithub.adapter.persistence.mapper.RolesEntityMapper;
import br.com.mutant.profilemanagementgithub.adapter.rest.controller.role.dto.RoleCreateRequest;
import br.com.mutant.profilemanagementgithub.adapter.rest.controller.role.dto.RoleResponse;
import br.com.mutant.profilemanagementgithub.adapter.rest.controller.role.mapper.RestRoleMapper;
import br.com.mutant.profilemanagementgithub.config.ControllerUnitTest;
import br.com.mutant.profilemanagementgithub.domain.model.role.Role;
import br.com.mutant.profilemanagementgithub.domain.ports.provided.role.CreateRoleUseCase;
import br.com.mutant.profilemanagementgithub.helpers.RolesFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
        RoleCreateRequest request = new RoleCreateRequest("NewRole");
        Role newRole = RolesFactory.generateRole(1L, "NewRole");
        RoleResponse roleResponse = RestRoleMapper.mapperToRoleResponse(newRole);

        when(createRoleUseCase.createNewRole(any())).thenReturn(newRole);

        mockMvc.perform(post("/api/role")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .with(csrf()))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(roleResponse)));
    }
}