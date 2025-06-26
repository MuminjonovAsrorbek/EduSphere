package uz.dev.edusphere.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DTO for {@link uz.dev.edusphere.entity.ParentStudentLink}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentStudentLinkDTO implements Serializable {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp createdAt;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long parentId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long studentId;
}