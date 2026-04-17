package com.oliver.biblioteca_java.controller;

import com.oliver.biblioteca_java.dto.MembroDto;
import com.oliver.biblioteca_java.entity.Membro;
import com.oliver.biblioteca_java.service.MembroService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/membro")
public class MembroController {

    private final MembroService membroServ;

    public MembroController(MembroService membroServ){
        this.membroServ = membroServ;
    }

    @PostMapping
    public ResponseEntity<Void> salvarMembro(@RequestBody MembroDto membroDto){
        membroServ.salvarMembro(membroDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<MembroDto> buscarMembroPorId(@PathVariable Long id){
        return ResponseEntity.ok(membroServ.buscarMembroPorId(id));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<MembroDto>> buscarMembroPorNome(@PathVariable String nome){
        return ResponseEntity.ok(membroServ.buscarMembroPorNome(nome));
    }

    @GetMapping
    public ResponseEntity<List<MembroDto>> buscarTodosMembro(){
        return ResponseEntity.ok(membroServ.buscarTodosMembro());
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Void> atualizarMembroPorId(@PathVariable Long id, @RequestBody  MembroDto membroDto){
        membroServ.atualizarMembroPorId(id,membroDto);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deletarMembroPorId(@PathVariable Long id){
        membroServ.deletarMembroPorId(id);
        return ResponseEntity.noContent().build();
    }
}
