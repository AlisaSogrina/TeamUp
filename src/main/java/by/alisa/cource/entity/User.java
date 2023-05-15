package by.alisa.cource.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name="userEntity")  // postgresql can't create table with name |user|
public class User implements UserDetails {

    public User(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, message = "Username length should be greater than 1")
    private String username;

    @Size(min = 2, message = "Password length should be greater than 1")
    private String password;

    @Transient
    private String passwordConfirm;  // double check just in <form>, won't be in db

    @ManyToMany(fetch = FetchType.EAGER)  // data will be loaded immediately
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Project> projects;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Project> projectsTakePart;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Project> projectsWannaTakePart;


//    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
//    private List<Comment> comments;
//
//
//    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
//    private List<Question> questions;
//
//
//    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
//    private List<Reaction> reactions;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return getRoles();
    }
}
