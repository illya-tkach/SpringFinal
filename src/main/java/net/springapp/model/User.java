package net.springapp.model;


import net.springapp.model.barbershop.Barber;
import net.springapp.model.barbershop.Client;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "user", uniqueConstraints=
@UniqueConstraint(columnNames={"user_name", "password"}))
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "user_name", unique = true, nullable = false)
    private String userName;

    @ManyToMany
    @JoinTable (name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> roles;

    @OneToOne(mappedBy = "userAccount")
    private Barber barber;

    @OneToOne(mappedBy = "userAccount")
    private Client client;

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public Barber getBarber() {
        return barber;
    }

    public Client getClient() {
        return client;
    }
}