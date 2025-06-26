package uz.dev.edusphere.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.dev.edusphere.dto.UserDTO;
import uz.dev.edusphere.dto.response.PageableDTO;
import uz.dev.edusphere.entity.User;
import uz.dev.edusphere.entity.template.AbsLongEntity;
import uz.dev.edusphere.exceptions.EntityAlreadyExist;
import uz.dev.edusphere.mapper.UserMapper;
import uz.dev.edusphere.repository.UserRepository;
import uz.dev.edusphere.service.template.UserService;

import java.util.List;
import java.util.Optional;

/**
 * Created by: asrorbek
 * DateTime: 6/25/25 23:33
 **/

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public PageableDTO getAllUsers(Integer page) {

        Sort sort = Sort.by(AbsLongEntity.Fields.id).ascending();

        Pageable pageable = PageRequest.of(page, 10, sort);

        Page<User> userPage = userRepository.findAll(pageable);

        List<User> users = userPage.getContent();

        List<UserDTO> userDTOS = userMapper.toDTO(users);

        return new PageableDTO(
                userPage.getSize(),
                userPage.getTotalElements(),
                userPage.getTotalPages(),
                !userPage.isLast(),
                !userPage.isFirst(),
                userDTOS
        );
    }

    @Override
    public UserDTO getUserById(Long id) {

        User user = userRepository.findByIdOrThrowException(id);

        return userMapper.toDTO(user);

    }

    @Override
    @Transactional
    public void createUser(UserDTO userDTO) {

        Optional<User> byEmail = userRepository.findByEmail(userDTO.getEmail());

        if (byEmail.isPresent())
            throw new EntityAlreadyExist("User already exist by this email", HttpStatus.CONFLICT);

        Optional<User> byUsername = userRepository.findByUsername(userDTO.getUsername());

        if (byUsername.isPresent())
            throw new EntityAlreadyExist("User with username already exist", HttpStatus.CONFLICT);

        User user = userMapper.toEntity(userDTO);

        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(Long userId, UserDTO userDTO) {

        User user = userRepository.findByIdOrThrowException(userId);

        userRepository.findByUsernameAndIdNotThrowException(userDTO.getUsername(), userId);

        userRepository.findByEmailAndIdNotThrowException(userDTO.getEmail(), userId);

        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        userRepository.save(user);

    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {

        User user = userRepository.findByIdOrThrowException(userId);

        userRepository.delete(user);

    }
}