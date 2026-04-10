package com.oliver.biblioteca_java.controller;

import com.oliver.biblioteca_java.dto.AutorDto;
import com.oliver.biblioteca_java.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autor")
public class AutorController {

    private final AutorService autorServ;

    public AutorController(AutorService autorServ){
        this.autorServ = autorServ;
    }

    @PostMapping
    public ResponseEntity<Void> salvarLivro(@RequestBody AutorDto autorDto){
        autorServ.salvarAutor(autorDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<AutorDto> buscarLivroPorId(@PathVariable Long id){
        return ResponseEntity.ok(autorServ.buscarAutorPorId(id));
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<AutorDto> buscarLivroPorTitulo(@PathVariable String nome){
        return ResponseEntity.ok(autorServ.buscarAutorPorNome(nome));
    }

    @GetMapping
    public ResponseEntity<List<AutorDto>>buscarTodosLivros(){
        return ResponseEntity.ok(autorServ.buscarTodosAutores());
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Void> atualizarLivroPorId(@PathVariable Long id, @RequestBody AutorDto autorDto){
        autorServ.atualizarAutorPorId(id,autorDto);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deletarLivroPorId(@PathVariable Long id){
        autorServ.deletarAutorPorId(id);
        return ResponseEntity.noContent().build();
    }

}
