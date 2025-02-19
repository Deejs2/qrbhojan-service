package com.menu.qrbhojan.template_customization.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class TemplateCustomization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(columnDefinition = "json")

    @Column(nullable = false, columnDefinition = "TEXT")
    private String customStyles;

    @Column(nullable = false)
    private String cafeSpecialId;
}