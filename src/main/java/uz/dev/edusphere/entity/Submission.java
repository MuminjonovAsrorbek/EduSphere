package uz.dev.edusphere.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import uz.dev.edusphere.entity.template.AbsLongEntity;
import uz.dev.edusphere.enums.SubmissionStatus;

import java.time.LocalDateTime;

/**
 * Created by: asrorbek
 * DateTime: 6/25/25 20:34
 **/

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@FieldNameConstants
public class Submission extends AbsLongEntity {

    @ManyToOne
    private Assignment assignment;

    @ManyToOne
    private User student;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private LocalDateTime submittedAt;

    @Enumerated(EnumType.STRING)
    private SubmissionStatus status;

    @OneToOne(mappedBy = "submission", cascade = CascadeType.ALL, orphanRemoval = true)
    private Grade grade;

}
