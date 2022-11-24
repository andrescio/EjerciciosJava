package com.example.block11uploaddownloadfiles.file.infraestructure.repository;

import com.example.block11uploaddownloadfiles.file.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Integer> {
    Optional<File> findByName(String name);
}
