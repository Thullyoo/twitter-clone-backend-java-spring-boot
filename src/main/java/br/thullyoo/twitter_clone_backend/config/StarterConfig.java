package br.thullyoo.twitter_clone_backend.config;

import br.thullyoo.twitter_clone_backend.domain.entity.Role;
import br.thullyoo.twitter_clone_backend.domain.entity.User;
import br.thullyoo.twitter_clone_backend.domain.repository.RoleRepository;
import br.thullyoo.twitter_clone_backend.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
public class StarterConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        this.userRepository.findByNickname("admin").ifPresentOrElse(
                user -> System.out.println("Admin já cadastrado"),
                () -> {
                    User user = new User();
                    user.setPassword(this.encoder.encode("12"));
                    user.setEmail("admin@gmail.com");
                    user.setNickname("admin");
                    user.setName("Admin");

                    Role role = roleRepository.findById(1).get();
                    HashSet<Role> roles = new HashSet<>();
                    roles.add(role);
                    user.setRoles(roles);
                    userRepository.save(user);
                }
        );
    }

}
