package br.thullyoo.twitter_clone_backend.domain.service;

import br.thullyoo.twitter_clone_backend.api.dto.JwtResponse;
import br.thullyoo.twitter_clone_backend.api.dto.UserRequest;
import br.thullyoo.twitter_clone_backend.api.dto.UserResponse;
import br.thullyoo.twitter_clone_backend.api.mapper.UserMapper;
import br.thullyoo.twitter_clone_backend.domain.entity.Role;
import br.thullyoo.twitter_clone_backend.domain.entity.User;
import br.thullyoo.twitter_clone_backend.domain.repository.RoleRepository;
import br.thullyoo.twitter_clone_backend.domain.repository.UserRepository;
import br.thullyoo.twitter_clone_backend.security.JWTService;
import br.thullyoo.twitter_clone_backend.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private RoleRepository roleRepository;

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
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        Role role = roleRepository.findById(0).get();
        HashSet<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        this.userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    @Override
    public JwtResponse loginUser(Authentication authentication) {
        String token = this.jwtService.generateToken(authentication);
        return new JwtResponse(4000L, token);
    }
}
