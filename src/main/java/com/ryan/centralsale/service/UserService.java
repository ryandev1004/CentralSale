package com.ryan.centralsale.service;

import com.ryan.centralsale.mapper.UserMapper;
import com.ryan.centralsale.model.User;
import com.ryan.centralsale.model.dto.UserCreateDTO;
import com.ryan.centralsale.model.dto.UserDTO;
import com.ryan.centralsale.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDTO createUser(UserCreateDTO userCreateDTO) {
        return userMapper.toDTO(userRepository.save(userMapper.toEntity(userCreateDTO)));
    }

    public UserDTO getUserById(UUID id) {
        return userMapper.toDTO(userRepository.findById(id).orElse(null));
    }

    public User findEntityById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

}
