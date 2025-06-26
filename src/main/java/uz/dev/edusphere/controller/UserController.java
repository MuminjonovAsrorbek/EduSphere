package uz.dev.edusphere.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.dev.edusphere.dto.UserDTO;
import uz.dev.edusphere.dto.response.PageableDTO;
import uz.dev.edusphere.service.template.UserService;

/**
 * Created by: asrorbek
 * DateTime: 6/25/25 23:28
 **/

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public PageableDTO getAllUsers(@RequestParam(required = false, defaultValue = "0", name = "page") Integer page) {

        return userService.getAllUsers(page);

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public UserDTO getUserById(@PathVariable Long id) {

        return userService.getUserById(id);

    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDTO userDTO) {

        userService.createUser(userDTO);

        return ResponseEntity.ok("User created successfully");

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> updateUser(@PathVariable Long id,
                                        @RequestBody @Valid UserDTO userDTO) {

        userService.updateUser(id, userDTO);

        return ResponseEntity.ok("User updated successfully");

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);

        return ResponseEntity.ok("User deleted successfully");

    }


}
