package com.oliver.biblioteca_java.controller;

import com.oliver.biblioteca_java.dto.GeneroDto;
import com.oliver.biblioteca_java.entity.Genero;
import com.oliver.biblioteca_java.service.GeneroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genero")
public class GeneroController {

    private final GeneroService generoServ;

    public GeneroController(GeneroService generoServ) {
        this.generoServ = generoServ;
    }

    @PostMapping
    public ResponseEntity<Void> salvarGenero(@RequestBody GeneroDto generoDto){
        generoServ.salvarGenero(generoDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<GeneroDto> buscarGeneroPorId(@PathVariable Long id){
        return ResponseEntity.ok(generoServ.buscarGeneroPorId(id));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<GeneroDto>> buscarGeneroPorNome(@PathVariable String nome){
        return ResponseEntity.ok(generoServ.buscarGeneroPorNome(nome));
    }

    @GetMapping
    public ResponseEntity<List<GeneroDto>> buscarTodosGeneros(){
        return ResponseEntity.ok(generoServ.buscarTodosGeneros());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarGeneroPorId(@PathVariable Long id, @RequestBody GeneroDto generoDto){
        generoServ.atualizarGeneroPorId(id, generoDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarGeneroPorId(@PathVariable Long id){
        generoServ.deletarGeneroPorId(id);
        return ResponseEntity.noContent().build();
    }
}
