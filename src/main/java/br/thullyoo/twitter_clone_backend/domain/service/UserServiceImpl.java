package br.thullyoo.twitter_clone_backend.domain.service;

import br.thullyoo.twitter_clone_backend.api.dto.UserRequest;
import br.thullyoo.twitter_clone_backend.api.dto.UserResponse;
import br.thullyoo.twitter_clone_backend.api.mapper.UserMapper;
import br.thullyoo.twitter_clone_backend.domain.entity.User;
import br.thullyoo.twitter_clone_backend.domain.repository.UserRepository;
import br.thullyoo.twitter_clone_backend.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponse registerUser(UserRequest userRequest) {
        if(this.userRepository.findByEmail(userRequest.email()).isPresent()){
            throw new RuntimeException("Email já cadastrado");
        }
        if (this.userRepository.findByNickname(userRequest.nickname()).isPresent()){
            throw new RuntimeException("Nickname já cadastrado");
        }
        User user = userMapper.toUser(userRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
        return userMapper.toUserResponse(user);
    }
}
