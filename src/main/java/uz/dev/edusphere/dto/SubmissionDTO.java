package uz.dev.edusphere.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.dev.edusphere.entity.Submission;
import uz.dev.edusphere.enums.SubmissionStatus;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * DTO for {@link Submission}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionDTO implements Serializable {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp createdAt;

    @NotNull
    private Long assignmentId;

    @NotNull
    private Long studentId;

    @NotBlank
    private String content;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime submittedAt;

    private SubmissionStatus status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private GradeDTO gradeDTO;
}