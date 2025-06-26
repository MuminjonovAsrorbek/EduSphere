package uz.dev.edusphere.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import uz.dev.edusphere.dto.SubmissionDTO;
import uz.dev.edusphere.entity.Submission;

import java.util.List;

/**
 * Created by: asrorbek
 * DateTime: 6/26/25 13:16
 **/

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {GradeMapper.class})
public interface SubmissionMapper {

    @Mapping(target = "studentId", source = "student.id")
    @Mapping(target = "assignmentId", source = "assignment.id")
    SubmissionDTO toDTO(Submission submission);

    List<SubmissionDTO> toDTO(List<Submission> submissions);
}

