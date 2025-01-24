package com.menu.qrbhojan.socialsite.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SocialIconLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long socialIconLinkId;

    @ManyToOne(cascade = CascadeType.ALL)
    private SocialIcon socialIcon;

    private String socialIconLinkUrl;
    private String cafeSpecialId;
}
