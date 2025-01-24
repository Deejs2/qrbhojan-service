package com.menu.qrbhojan.config;

import com.menu.qrbhojan.constant.SystemMessage;
import com.menu.qrbhojan.role.entity.Role;
import com.menu.qrbhojan.role.repository.RoleRepository;
import com.menu.qrbhojan.socialsite.entity.SocialIcon;
import com.menu.qrbhojan.socialsite.repository.SocialIconRepository;
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
    private final SocialIconRepository socialIconRepository;

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
            user.setFullName("QR Bhojan");
            user.setEmail("qrbhojan@gmail.com");
            user.setPhone("9841234567");
            user.setAddress("Kathmandu, Nepal");
            user.setPassword(new BCryptPasswordEncoder().encode("qrbhojan@123"));
            Role role = roleRepository.findByName(Role.ROLE_SUPER_ADMIN).orElseThrow(
                    () -> new RuntimeException(SystemMessage.ROLE_NOT_FOUND)
            );
            user.setRole(List.of(role));
            user.setIsActive(true);
            user.setIsDeleted(false);
            userRepository.save(user);
            log.info("Super admin role user created");
        }

        if(socialIconRepository.findAll().isEmpty()){
            createSocialIcon("Facebook", "bi bi-facebook");
            createSocialIcon("Instagram", "bi bi-instagram");
            createSocialIcon("WhatsApp", "bi bi-whatsapp");
            createSocialIcon("TikTok", "bi bi-tiktok");
            createSocialIcon("YouTube", "bi bi-youtube");
        }
        
    }

    private void createSocialIcon(String socialIconName, String socialIconClass){
        SocialIcon socialIcon = new SocialIcon();
        socialIcon.setSocialIconName(socialIconName);
        socialIcon.setSocialIconClass(socialIconClass);
        socialIconRepository.save(socialIcon);
    }
}
