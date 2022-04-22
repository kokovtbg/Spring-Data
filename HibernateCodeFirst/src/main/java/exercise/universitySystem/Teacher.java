package exercise.universitySystem;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher extends Person {
    private String email;
    @Column(name = "salary_per_hour")
    private Double salaryPerHour;
    @OneToMany(mappedBy = "teacher", targetEntity = Course.class)
    private Set<Course> courses;
}
