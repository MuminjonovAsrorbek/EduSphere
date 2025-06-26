package uz.dev.edusphere.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * DTO for {@link uz.dev.edusphere.entity.Grade}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeDTO implements Serializable {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp createdAt;

    @NotNull
    private Long submissionId;

    @NotNull
    private Integer score;

    private String teacherComment;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime gradedAt;
}