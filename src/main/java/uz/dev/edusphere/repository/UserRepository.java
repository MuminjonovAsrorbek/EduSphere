package uz.dev.edusphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import uz.dev.edusphere.entity.User;
import uz.dev.edusphere.exceptions.EntityAlreadyExist;
import uz.dev.edusphere.exceptions.EntityNotFoundException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    default User findByIdOrThrowException(Long id) {

        return findById(id).orElseThrow(() -> new EntityNotFoundException("User not found wit ID : " + id, HttpStatus.NOT_FOUND));

    }

    Optional<User> findByEmail(String email);

    default void findByUsernameAndIdNotThrowException(String username, Long id) {

        Optional<User> optionalUser = findByUsername(username);

        if (optionalUser.isPresent() && !optionalUser.get().getId().equals(id))
            throw new EntityAlreadyExist("User with username " + username + " already exist", HttpStatus.CONFLICT);

    }

    default void findByEmailAndIdNotThrowException(String email, Long id) {

        Optional<User> optionalUser = findByEmail(email);

        if (optionalUser.isPresent() && !optionalUser.get().getId().equals(id))
            throw new EntityAlreadyExist("User with email " + email + " already exist", HttpStatus.CONFLICT);

    }

    boolean existsByUsername(String admin);
}