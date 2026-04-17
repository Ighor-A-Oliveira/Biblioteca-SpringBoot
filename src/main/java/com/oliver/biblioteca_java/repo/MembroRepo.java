package com.oliver.biblioteca_java.repo;

import com.oliver.biblioteca_java.entity.Membro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembroRepo extends JpaRepository<Membro,Long> {
    List<Membro> findByNome(String nome);
}

