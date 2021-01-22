package com.cybertek.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.agent.builder.AgentBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userName;
    private String password;
    private boolean active;
    private String roles;
    private String permissions;

    public User(String userName, String password, String roles, String permissions) {
        this.userName = userName;
        this.password = password;
        this.active = true;
        this.roles = roles;
        this.permissions = permissions;
    }

    public List<String> getRoleList() {
        if (this.roles.length()>0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getPermissionList() {
        if (this.permissions.length()>0) {
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }
}
