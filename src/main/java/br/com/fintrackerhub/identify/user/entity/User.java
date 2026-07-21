package br.com.fintrackerhub.identify.user.entity;

import br.com.fintrackerhub.common.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    protected UserEntity() {}

    protected UserEntity(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static UserEntity create(String email, String password) {
        return new UserEntity(email, password);
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    protected void setPassword(String password) {
        this.password = password;
    }
}