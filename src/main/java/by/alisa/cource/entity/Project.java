package by.alisa.cource.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name="Project")
public class Project {

    public Project(String name, String text) {
        setName(name);
        setText(text);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, message = "Project name length should be greater than 2")
    private String name;

    @Size(min = 3, message = "Project length should be greater than 2")
    @Column(columnDefinition = "TEXT")
    private String text;

//    @Size(min = 2, message = "Project status length should be greater than 1")
//    @Column(columnDefinition = "TEXT")
//    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "projects_users_take_part",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_entity_id"))
    private Set<User> usersTakePart;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "projects_users_wanna_take_part",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_entity_id"))
    private Set<User> usersWannaTakePart;
}
