package com.ryan.centralsale.service;

import com.ryan.centralsale.mapper.UserMapper;
import com.ryan.centralsale.model.dto.UserCreateDTO;
import com.ryan.centralsale.model.dto.UserDTO;
import com.ryan.centralsale.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDTO createUser(UserCreateDTO userCreateDTO) {
        return userMapper.toDTO(userRepository.save(userMapper.toEntity(userCreateDTO)));
    }

}
