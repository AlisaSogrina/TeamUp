package by.alisa.cource.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

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

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
}
