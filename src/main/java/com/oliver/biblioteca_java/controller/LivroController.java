package com.oliver.biblioteca_java.controller;


import com.oliver.biblioteca_java.dto.LivroDto;
import com.oliver.biblioteca_java.service.LivroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroController {

    private final LivroService livroServ;

    public LivroController(LivroService livroServ){
        this.livroServ = livroServ;
    }

    @PostMapping
    public ResponseEntity<Void> salvarLivro(@RequestBody LivroDto livroDto){
        livroServ.salvarLivro(livroDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<LivroDto> buscarLivroPorId(@PathVariable Long id){
        return ResponseEntity.ok(livroServ.buscarLivroPorId(id));
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<LivroDto> buscarLivroPorIsbn(@PathVariable String isbn){
        return ResponseEntity.ok(livroServ.buscarLivroPorIsbn(isbn));
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<LivroDto> buscarLivroPorTitulo(@PathVariable String titulo){
        return ResponseEntity.ok(livroServ.buscarLivroPorTitulo(titulo));
    }

    @GetMapping
    public ResponseEntity<List<LivroDto> >buscarTodosLivros(){
        return ResponseEntity.ok(livroServ.buscarTodosLivros());
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Void> atualizarLivroPorId(@PathVariable Long id, @RequestBody LivroDto livroDto){
        livroServ.atualizarLivroPorId(id,livroDto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/isbn/{isbn}")
    public ResponseEntity<Void> atualizarLivroPorIsbn(@PathVariable String isbn, @RequestBody LivroDto livroDto){
        livroServ.atualizarLivroPorIsbn(isbn,livroDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        livroServ.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/isbn/{isbn}")
    public ResponseEntity<Void> deletarPorIsbn(@PathVariable String isbn){
        livroServ.deletarPorIsbn(isbn);
        return ResponseEntity.noContent().build();
    }
}
