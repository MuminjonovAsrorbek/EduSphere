package uz.dev.edusphere.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import uz.dev.edusphere.entity.template.AbsLongEntity;

import java.time.LocalDateTime;

/**
 * Created by: asrorbek
 * DateTime: 6/25/25 20:37
 **/

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@FieldNameConstants
public class Grade extends AbsLongEntity {

    @OneToOne
    private Submission submission;

    @Column(nullable = false)
    private Integer score;

    @Column(columnDefinition = "TEXT")
    private String teacherComment;

    @Column(nullable = false)
    private LocalDateTime gradedAt;

}
