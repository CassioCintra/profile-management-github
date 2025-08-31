package br.com.mutant.profilemanagementgithub.adapter.rest.controller.user;

import br.com.mutant.profilemanagementgithub.adapter.rest.controller.user.dto.UserCreateRequest;
import br.com.mutant.profilemanagementgithub.adapter.rest.controller.user.dto.UserResponse;
import br.com.mutant.profilemanagementgithub.config.ControllerUnitTest;
import br.com.mutant.profilemanagementgithub.domain.model.user.ApplicationUser;
import br.com.mutant.profilemanagementgithub.domain.ports.provided.user.CreateUserUseCase;
import br.com.mutant.profilemanagementgithub.helpers.ApplicationUsersFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ControllerUnitTest(controllers =  { CreateUserController.class })
class CreateUserControllerTest {

    @MockitoBean
    private CreateUserUseCase createUserUseCase;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_create_user_and_retorn_201_created_and_body() throws Exception {
        UserCreateRequest request = new UserCreateRequest("login", "password", "www.url.com");
        ApplicationUser user = ApplicationUsersFactory.generateGitHubUser(1L, "login", "www.url.com");
        UserResponse response = new UserResponse(1L, "login", "www.url.com", List.of());

        when(createUserUseCase.create(any())).thenReturn(user);

        mockMvc.perform(
                post("/api/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
                )
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }
}