package br.com.mutant.profilemanagementgithub.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private Long id;
    private String name;

    public Role(String name) {
        this.name = name;
    }
}
