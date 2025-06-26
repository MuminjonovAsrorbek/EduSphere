package uz.dev.edusphere.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import uz.dev.edusphere.entity.template.AbsLongEntity;

import java.time.LocalDate;

/**
 * Created by: asrorbek
 * DateTime: 6/25/25 20:28
 **/

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@FieldNameConstants
public class Enrollment extends AbsLongEntity {

    @ManyToOne
    private User student;

    @ManyToOne
    private Course course;

    @Column(nullable = false)
    private LocalDate enrollmentDate;

}
