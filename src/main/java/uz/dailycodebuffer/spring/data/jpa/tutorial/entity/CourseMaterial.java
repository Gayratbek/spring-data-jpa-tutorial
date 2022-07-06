package uz.dailycodebuffer.spring.data.jpa.tutorial.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CourseMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private  String url;

    @OneToOne
            (
                    cascade = CascadeType.ALL,
                    fetch = FetchType.LAZY,optional = false


            )
    @JoinColumn(
            name = "course_id",
            referencedColumnName = "id"
    )
    private Course course;


}
