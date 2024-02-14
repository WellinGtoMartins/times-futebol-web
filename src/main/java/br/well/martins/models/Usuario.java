package br.well.martins.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.Objects;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @Length(max = 10)
    @NotBlank
    @Column(name = "login", nullable = false)
    private String login;

    @NotBlank
    @Length(max = 10)
    @Column(name = "senha", nullable = false)
    private String senha;

    @NotBlank
    @Length(max = 80)
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public Usuario() {

    }

    public boolean isAdmin() {
        return Role.ADMIN.equals(role);
    }


    public boolean isUser() {
        return Role.USER.equals(role);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(login, usuario.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }
}
