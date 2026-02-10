package hexlet.code.service;

import hexlet.code.database.repository.UserRepository;
import hexlet.code.dto.user.UserCreateDTO;
import hexlet.code.dto.user.UserDTO;
import hexlet.code.dto.user.UserUpdateDTO;
import hexlet.code.exception.NotFoundException;
import hexlet.code.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::map)
                .toList();
    }

    @Override
    public UserDTO getUserById(long id) {
        return userMapper.map(userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " not found!")));
    }

    @Override
    public UserDTO createUser(UserCreateDTO userData) {
        return userMapper.map(userRepository.save(userMapper.map(userData)));
    }

    @Override
    public UserDTO updateUser(long id, UserUpdateDTO userData) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " not found!"));
        userMapper.update(userData, user);
        return userMapper.map(userRepository.save(user));
    }

    @Override
    public void deleteUser(long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " not found!"));
        userRepository.delete(user);
    }
}





