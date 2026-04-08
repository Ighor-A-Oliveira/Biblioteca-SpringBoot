package com.oliver.biblioteca_java.repo;

import com.oliver.biblioteca_java.entity.Autor;
import com.oliver.biblioteca_java.entity.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GeneroRepo extends JpaRepository<Genero,Long> {
    List<Genero> findByNome(String nome);
}
