package br.com.mutant.profilemanagementgithub.domain.model.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    private String login;
    private String password;
}
