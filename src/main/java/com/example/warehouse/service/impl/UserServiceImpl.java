package com.example.warehouse.service.impl;

import com.example.warehouse.dto.mapper.UserMapper;
import com.example.warehouse.dto.request.UserRegistrationRequest;
import com.example.warehouse.dto.request.UserRequest;
import com.example.warehouse.dto.response.UserResponse;
import com.example.warehouse.entity.Admin;
import com.example.warehouse.entity.Staff;
import com.example.warehouse.entity.User;
import com.example.warehouse.enums.UserRole;
import com.example.warehouse.exceptions.UnsupportedUserRoleException;
import com.example.warehouse.exceptions.UserNotFoundByEmailException;
import com.example.warehouse.exceptions.UserNotFoundByIdException;
import com.example.warehouse.repository.UserRepository;
import com.example.warehouse.service.contract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import static com.example.warehouse.security.AuthUtils.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponse addUser(UserRegistrationRequest urr) {

            UserRole role = urr.userRole();
            User user;
            if (role == UserRole.STAFF) {
                user = userMapper.userToEntity(urr, new Staff());
            } else if (role == UserRole.ADMIN) {
                user = userMapper.userToEntity(urr, new Admin());
            } else {
                throw new IllegalArgumentException("Unsupported role: " + role);
            }
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userRepository.save(user);
            return userMapper.userToResponse(user);
    }

    @Override
    public UserResponse updateUser(UserRequest request, String userId) {
        User exUser = getCurrentUserName()
                .map(userName -> userRepository.findByEmail(userName)
                                                        .orElseThrow(() ->new UserNotFoundByEmailException("User Not Found!!")))
                .orElseThrow(() ->new UnsupportedUserRoleException("User Not Authenticated"));
       User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundByIdException("User Not Found!!"));
       user = userMapper.requestToEntity(request,user);

       String encodedPassword = passwordEncoder.encode(user.getPassword());
       user.setPassword(encodedPassword);
       userRepository.save(user);
       return userMapper.userToResponse(user);
    }

    @Override
    public UserResponse findUserById() {
        return getCurrentUserName()
                .map(userName -> userRepository.findByEmail(userName)
                        .orElseThrow(() ->new UserNotFoundByEmailException("User Not Found!!")))
                .map(userMapper::userToResponse)
                .orElseThrow(() ->new UnsupportedUserRoleException("User Not Authenticated"));
    }

    @Override
    public UserResponse deleteUserById(String userId) {
       User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundByIdException("UserId Not Found!!"));
        userRepository.delete(user);
        return userMapper.userToResponse(user);
    }
}
