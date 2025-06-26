package uz.dev.edusphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import uz.dev.edusphere.entity.ParentStudentLink;
import uz.dev.edusphere.entity.User;
import uz.dev.edusphere.exceptions.EntityNotFoundException;

public interface ParentStudentLinkRepository extends JpaRepository<ParentStudentLink, Long> {

    default ParentStudentLink findByIdOrThrowException(Long id) {

        return findById(id).orElseThrow(() -> new EntityNotFoundException("ParentStudentLink not found with ID : " + id, HttpStatus.NOT_FOUND));

    }

    boolean existsByParentAndStudent(User parent, User student);


}