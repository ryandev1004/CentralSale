package com.ryan.centralsale.service;

import com.ryan.centralsale.mapper.UserMapper;
import com.ryan.centralsale.model.User;
import com.ryan.centralsale.model.dto.LoginRequestDTO;
import com.ryan.centralsale.model.dto.LoginResponseDTO;
import com.ryan.centralsale.model.dto.UserCreateDTO;
import com.ryan.centralsale.model.dto.UserDTO;
import com.ryan.centralsale.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
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
        if(userRepository.findAll().stream().anyMatch(u -> u.getEmail().equals(userCreateDTO.getEmail()))){
            throw new EntityExistsException("User already exists with email: " + userCreateDTO.getEmail());
        }
        return userMapper.toDTO(userRepository.save(userMapper.toEntity(userCreateDTO)));
    }

    public LoginResponseDTO loginUser(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByEmail(loginRequestDTO.getEmail());
        if (user == null) {
            return new LoginResponseDTO(false, null, "User not found");
        }
        if (!user.getPassword().equals(loginRequestDTO.getPassword())) {
            return new LoginResponseDTO(false, null, "Invalid password");
        }
        return new LoginResponseDTO(true, userMapper.toDTO(user), null);
    }

    public User findEntityById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

}
