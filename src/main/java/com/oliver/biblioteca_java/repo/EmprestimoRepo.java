package com.oliver.biblioteca_java.repo;

import com.oliver.biblioteca_java.entity.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepo extends JpaRepository<Emprestimo,Long> {
}
