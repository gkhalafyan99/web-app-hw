package com.example.softwareEng.presentation;

import java.util.ArrayList;
import com.example.softwareEng.CreateUserDto;
import com.example.softwareEng.UserDto;
import com.example.softwareEng.persistence.User;
import com.example.softwareEng.persistence.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    //localhost:8888/users
    public ResponseEntity<UserDto> create(@RequestBody CreateUserDto dto) {

        final User user = new User();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setEmail(dto.getEmail());

        final User savedUser = userRepository.save(user);

        final UserDto responseDto = new UserDto();
        responseDto.setId(savedUser.getId());
        responseDto.setName(savedUser.getName());
        responseDto.setSurname(savedUser.getSurname());
        responseDto.setEmail(savedUser.getEmail());

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    //127.0.0.1:8888/users
    public ResponseEntity<Iterable<UserDto>> getAll() {

        final  Iterable<User> users = userRepository.findAll();
        final ArrayList<UserDto> result = new ArrayList<>();

        UserDto dto;

        for (User u : users) {
            dto = new UserDto();

            dto.setId(u.getId());
            dto.setName(u.getName());
            dto.setSurname(u.getSurname());
            dto.setEmail(u.getEmail());

            result.add(dto);
        }

        return ResponseEntity.ok(result);
    }


}
