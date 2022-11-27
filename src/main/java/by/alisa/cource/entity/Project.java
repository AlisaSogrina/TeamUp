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

    public Project(String progectName) {
        setProjectName(progectName);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, message = "Project name length should be greater than 2")
    private String projectName;


}
