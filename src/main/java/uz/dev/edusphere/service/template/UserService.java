package uz.dev.edusphere.service.template;

import uz.dev.edusphere.dto.UserDTO;
import uz.dev.edusphere.dto.response.PageableDTO;

/**
 * Created by: asrorbek
 * DateTime: 6/25/25 23:33
 **/

public interface UserService {

    PageableDTO getAllUsers(Integer page);

    UserDTO getUserById(Long id);

    void createUser(UserDTO userDTO);

    void updateUser(Long userId, UserDTO userDTO);

    void deleteUser(Long userId);

}
