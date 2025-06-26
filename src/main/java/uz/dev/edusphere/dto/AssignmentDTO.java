package uz.dev.edusphere.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * DTO for {@link uz.dev.edusphere.entity.Assignment}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentDTO implements Serializable {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp createdAt;

    @NotBlank
    private String title;

    private String description;

    @NotNull
    private LocalDateTime deadline;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long courseId;
}