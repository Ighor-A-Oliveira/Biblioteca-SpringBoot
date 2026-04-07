package com.oliver.biblioteca_java.repo;

import com.oliver.biblioteca_java.entity.Autor;
import com.oliver.biblioteca_java.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutorRepo extends JpaRepository<Autor,Long> {
    Optional<Autor> findById(Long id);

}
