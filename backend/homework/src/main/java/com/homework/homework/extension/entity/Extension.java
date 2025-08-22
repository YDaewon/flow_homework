package com.homework.homework.extension.entity;

import com.homework.homework.common.entity.BaseEntity;
import com.homework.homework.extension.entity.dto.CustomExtensionDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Extension extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 20)
    @Column(nullable = false, unique = true, length = 20)
    private String name;

    @Column(nullable = false)
    private boolean isActive;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private ExtensionType type;

    public void toggleActive(boolean active) {
        this.isActive = active;
    }

    public static Extension from(String name) {
        return Extension.builder()
                .name(name)
                .isActive(true)
                .type(ExtensionType.CUSTOM)
                .build();
    }
}
