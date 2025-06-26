package uz.dev.edusphere.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * DTO for {@link uz.dev.edusphere.entity.Enrollment}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentDTO implements Serializable {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp createdAt;

    @NotNull
    private Long studentId;

    @NotNull
    private Long courseId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate enrollmentDate;
}