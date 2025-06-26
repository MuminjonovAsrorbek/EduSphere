package uz.dev.edusphere.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.dev.edusphere.dto.GradeDTO;
import uz.dev.edusphere.dto.ParentStudentLinkDTO;
import uz.dev.edusphere.dto.response.PageableDTO;
import uz.dev.edusphere.entity.Grade;
import uz.dev.edusphere.entity.ParentStudentLink;
import uz.dev.edusphere.entity.User;
import uz.dev.edusphere.entity.template.AbsLongEntity;
import uz.dev.edusphere.mapper.GradeMapper;
import uz.dev.edusphere.repository.GradeRepository;
import uz.dev.edusphere.repository.ParentStudentLinkRepository;
import uz.dev.edusphere.repository.UserRepository;
import uz.dev.edusphere.service.template.ParentStudentService;

import java.util.List;

/**
 * Created by: asrorbek
 * DateTime: 6/26/25 10:31
 **/

@Service
@RequiredArgsConstructor
public class ParentStudentServiceImpl implements ParentStudentService {

    private final ParentStudentLinkRepository parentStudentLinkRepository;

    private final UserRepository userRepository;

    private final GradeRepository gradeRepository;

    private final GradeMapper gradeMapper;

    @Override
    @Transactional
    public void create(ParentStudentLinkDTO dto) {

        User parentUser = userRepository.findByIdOrThrowException(dto.getParentId());

        User studentUser = userRepository.findByIdOrThrowException(dto.getStudentId());

        ParentStudentLink parentStudentLink = new ParentStudentLink();

        parentStudentLink.setParent(parentUser);
        parentStudentLink.setStudent(studentUser);

        parentStudentLinkRepository.save(parentStudentLink);

    }

    @Override
    @Transactional
    public void delete(Long id) {

        ParentStudentLink parentStudentLink = parentStudentLinkRepository.findByIdOrThrowException(id);

        parentStudentLinkRepository.delete(parentStudentLink);

    }

    @Override
    public PageableDTO getMyChildrenGrades(Long childrenId, Integer page) {

        Sort sort = Sort.by(AbsLongEntity.Fields.id).ascending();

        Pageable pageable = PageRequest.of(page, 10, sort);

        Page<Grade> gradePage =
                gradeRepository.findAllBySubmissionStudentId(childrenId, pageable);

        List<Grade> grades = gradePage.getContent();

        List<GradeDTO> gradeDTOS = gradeMapper.toDTO(grades);

        return new PageableDTO(
                gradePage.getSize(),
                gradePage.getTotalElements(),
                gradePage.getTotalPages(),
                !gradePage.isLast(),
                !gradePage.isFirst(),
                gradeDTOS
        );
    }
}
