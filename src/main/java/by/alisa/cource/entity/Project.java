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

    public Project(String projectName, String projectText) {
        setProjectName(projectName);
        setProjectText(projectText);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, message = "Project name length should be greater than 2")
    private String projectName;

    @Size(min = 3, message = "Project length should be greater than 2")
    @Column(columnDefinition = "TEXT")
    private String projectText;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
}
