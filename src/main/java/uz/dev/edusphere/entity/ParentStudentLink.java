package uz.dev.edusphere.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import uz.dev.edusphere.entity.template.AbsLongEntity;

/**
 * Created by: asrorbek
 * DateTime: 6/25/25 20:25
 **/


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@FieldNameConstants
public class ParentStudentLink extends AbsLongEntity {

    @ManyToOne
    private User parent;

    @ManyToOne
    private User student;
}
