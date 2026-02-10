package hexlet.code.service;

import hexlet.code.dto.user.UserCreateDTO;
import hexlet.code.dto.user.UserDTO;
import hexlet.code.dto.user.UserUpdateDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();

    UserDTO getUserById(long id);

    UserDTO createUser(UserCreateDTO userData);

    UserDTO updateUser(long id, UserUpdateDTO userData);

    void deleteUser(long id);
}

