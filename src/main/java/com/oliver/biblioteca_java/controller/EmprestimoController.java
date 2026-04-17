package com.oliver.biblioteca_java.controller;

import com.oliver.biblioteca_java.dto.EmprestimoDto;
import com.oliver.biblioteca_java.entity.Emprestimo;
import com.oliver.biblioteca_java.entity.Livro;
import com.oliver.biblioteca_java.entity.Membro;
import com.oliver.biblioteca_java.service.EmprestimoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    private final EmprestimoService empServ;

    public EmprestimoController(EmprestimoService empServ){
        this.empServ = empServ;
    }

    @PostMapping
    public ResponseEntity<Void> realizarEmprestimo(@RequestBody EmprestimoDto empDto){
        empServ.realizarEmprestimo(empDto.getMembroId(), empDto.getLivroId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/devolver/{emprestimoId}")
    public ResponseEntity<Void> devolverLivro(@PathVariable Long emprestimoId){
        empServ.devolverLivro(emprestimoId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(empServ.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<EmprestimoDto>> buscarTodosEmprestimos() {
        return ResponseEntity.ok(empServ.buscarTodosEmprestimos());
    }
}
