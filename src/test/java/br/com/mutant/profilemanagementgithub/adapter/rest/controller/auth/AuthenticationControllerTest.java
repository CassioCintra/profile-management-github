package br.com.mutant.profilemanagementgithub.adapter.rest.controller.auth;

import br.com.mutant.profilemanagementgithub.adapter.rest.controller.auth.dto.JwtResponse;
import br.com.mutant.profilemanagementgithub.adapter.rest.controller.auth.dto.LoginRequest;
import br.com.mutant.profilemanagementgithub.adapter.security.JwtTokenUtils;
import br.com.mutant.profilemanagementgithub.config.ControllerUnitTest;
import br.com.mutant.profilemanagementgithub.domain.model.GitHubUser;
import br.com.mutant.profilemanagementgithub.domain.ports.provided.auth.AuthenticationUseCase;
import br.com.mutant.profilemanagementgithub.helpers.GitHubUsersFactory;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ControllerUnitTest(controllers = AuthenticationController.class)
class AuthenticationControllerTest {

    @MockitoBean
    private AuthenticationUseCase authenticationUseCase;

    @MockitoBean
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_return_jwt_token_and_200_ok() throws Exception {
        GitHubUser user = GitHubUsersFactory.generateGitHubUser(1L, "login", "www.url.com");
        LoginRequest loginRequest = new LoginRequest(user.getLogin(), user.getPassword());
        JwtResponse response = new JwtResponse("token");
        when(authenticationUseCase.authorize(any())).thenReturn(user);
        when(jwtTokenUtils.generateToken(any())).thenReturn("token");

        mockMvc.perform(
                    post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest))
                        .with(csrf())
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

}