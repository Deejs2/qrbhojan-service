package com.menu.qrbhojan.socialsite;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SocialIcon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long socialIconId;
    private String socialIconName;
    private String socialIconClass;
}
