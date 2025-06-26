package uz.dev.edusphere.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import uz.dev.edusphere.entity.template.AbsLongEntity;

import java.time.LocalDateTime;

/**
 * Created by: asrorbek
 * DateTime: 6/25/25 20:30
 **/


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@FieldNameConstants
public class Assignment extends AbsLongEntity {

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private LocalDateTime deadline;

    @ManyToOne
    private Course course;
}
