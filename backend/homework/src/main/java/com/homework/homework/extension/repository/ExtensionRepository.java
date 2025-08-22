package com.homework.homework.extension.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homework.homework.extension.entity.Extension;

@Repository
public interface ExtensionRepository extends JpaRepository<Extension, Long> {

    Optional<Extension> findByName(String name);
    
}
