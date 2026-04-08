package com.oliver.biblioteca_java.repo;

import com.oliver.biblioteca_java.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LivroRepo extends JpaRepository<Livro,Long> {

    List<Livro> findByGenero(String genero);
    List<Livro> findByAutoresId(Long id);
    Optional<Livro> findByIsbn(String isbn);
    Optional<Livro> findByTitulo(String titulo);
    void deleteById(Long id);
    void deleteByIsbn(String isbn);

}
