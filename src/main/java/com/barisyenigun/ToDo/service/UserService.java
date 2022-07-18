package com.barisyenigun.ToDo.service;

import com.barisyenigun.ToDo.entity.User;
import com.barisyenigun.ToDo.exception.UserNotFoundException;
import com.barisyenigun.ToDo.repository.UserRepository;
import com.barisyenigun.ToDo.request.UserUpdateRequest;
import com.barisyenigun.ToDo.response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Long getAuthenticatedUserId(){
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal.equals("anonymousUser")){
            throw new IllegalStateException();
        }
        return Long.parseLong(principal);
    }

    public Optional<User> getAuthenticatedUser(){
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal.equals("anonymousUser")){
            return Optional.empty();
        }
        return userRepository.findById(Long.parseLong(principal));
    }

    public UserResponse getUser(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
        return UserResponse.fromEntity(user);
    }

    public void updateUser(Long id, UserUpdateRequest body){
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
        user.setUsername(body.getUsername());
        user.setPassword(body.getPassword());
        userRepository.save(user);
    }

}
