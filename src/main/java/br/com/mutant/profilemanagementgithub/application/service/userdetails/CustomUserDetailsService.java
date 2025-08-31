package br.com.mutant.profilemanagementgithub.application.service.userdetails;

import br.com.mutant.profilemanagementgithub.domain.model.GitHubUser;
import br.com.mutant.profilemanagementgithub.domain.ports.required.GitHubUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final GitHubUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        GitHubUser user = userRepository.findByLogin(login);

        Collection<? extends GrantedAuthority> authorities = getAuthorities(user);

        return User.builder()
                .username(user.getLogin())
                .password("")
                .authorities(authorities)
                .build();
    }

    private Collection<? extends GrantedAuthority> getAuthorities(GitHubUser user) {
        return user.getRoles() == null || user.getRoles().isEmpty()?
                Collections.emptyList():
                user.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                    .toList();
    }

}
