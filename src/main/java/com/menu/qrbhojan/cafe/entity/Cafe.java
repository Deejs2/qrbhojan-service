package com.menu.qrbhojan.cafe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cafe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cafeId;

    private String cafeName;
    private String cafeLogo;
    private String cafeLocation;
    private String cafeDescription;
    private String cafeBanner;
    private String cafeContact;
    @Column(unique = true)
    private String cafeEmail;
    private String cafeSpecialId;
    private Long userId;
    private String cafeOpeningHours;
}
