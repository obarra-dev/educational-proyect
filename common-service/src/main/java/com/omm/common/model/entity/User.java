package com.omm.common.model.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 20)
    private String username;
    @Column(length = 60)
    private String password;
    @Column(unique = true, length = 100)
    private String email;
    private String firstName;
    private String lastName;
    private Boolean enable;
    private Integer intent;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles"
    ,joinColumns = @JoinColumn(name = "users_id")
    ,inverseJoinColumns = @JoinColumn(name = "roles_id")
    ,uniqueConstraints = {@UniqueConstraint(columnNames = {"users_id","roles_id"})})
    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

	public Integer getIntent() {
		return intent;
	}

	public void setIntent(Integer intent) {
		this.intent = intent;
	}
}
