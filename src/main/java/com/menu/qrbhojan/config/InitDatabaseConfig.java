package com.menu.qrbhojan.config;

import com.menu.qrbhojan.constant.SystemMessage;
import com.menu.qrbhojan.role.entity.Role;
import com.menu.qrbhojan.role.repository.RoleRepository;
import com.menu.qrbhojan.user.entity.Users;
import com.menu.qrbhojan.user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class InitDatabaseConfig {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @PostConstruct
    public void initDatabase() {
        log.info("Initializing database");
        if(roleRepository.findAll().isEmpty()) {
            log.info("Creating roles");
            List<Role> roles = List.of(
                    new Role(null, "ROLE_SUPER_ADMIN", "Super Admin Role"),
                    new Role(null, "ROLE_ADMIN", "Admin Role")
            );
            roleRepository.saveAll(roles);
            log.info("Roles created");
        }

        if(userRepository.findAll().isEmpty()) {
            log.info("Creating super admin role user");
            Users user = new Users();
            user.setUsername("@qrbhojan");
            user.setFullName("QR Bhojan");
            user.setEmail("qrbhojan@gmail.com");
            user.setPhone("9841234567");
            user.setPassword(new BCryptPasswordEncoder().encode("qrbhojan@123"));
            Role role = roleRepository.findByName(Role.ROLE_SUPER_ADMIN).orElseThrow(
                    () -> new RuntimeException(SystemMessage.ROLE_NOT_FOUND)
            );
            user.setRole(role);
            userRepository.save(user);
            log.info("Super admin role user created");
        }
    }
}
