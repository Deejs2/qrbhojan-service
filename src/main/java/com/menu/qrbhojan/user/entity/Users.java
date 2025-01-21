package com.menu.qrbhojan.user.entity;

import com.menu.qrbhojan.cafe.entity.Cafe;
import com.menu.qrbhojan.role.entity.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String profileImage;
    private String fullName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(unique = true)
    private String phone;
    private String address;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> role;
    private Boolean isActive = true;
    private Boolean isDeleted = false;
    private Boolean isVerified = false;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Cafe cafe;
}
