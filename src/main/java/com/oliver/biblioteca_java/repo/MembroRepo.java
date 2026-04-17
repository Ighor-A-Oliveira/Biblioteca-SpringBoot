package com.oliver.biblioteca_java.repo;

import com.oliver.biblioteca_java.entity.Membro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembroRepo extends JpaRepository<Membro,Long> {
    List<Membro> findByNome(String nome);
}

