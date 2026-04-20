package com.oliver.biblioteca_java.service;

import com.oliver.biblioteca_java.dto.AutorDto;
import com.oliver.biblioteca_java.entity.Autor;
import com.oliver.biblioteca_java.exception.AutorNaoEncontradoException;
import com.oliver.biblioteca_java.repo.AutorRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class AutorService {

    private final AutorRepo autorRepo;

    public AutorService(AutorRepo autorRepo) {
        this.autorRepo = autorRepo;
    }

    @Transactional
    public void salvarAutor(AutorDto autorDto){
        Autor autor = new Autor();
        autor.setNome(autorDto.getNome());
        autor.setNacionalidade(autorDto.getNacionalidade());

        autorRepo.saveAndFlush(autor);
    }

    public AutorDto buscarAutorPorId(Long id){
        //encontrando a entidade autor
        Autor autor = autorRepo.findById(id).orElseThrow(() -> new AutorNaoEncontradoException(id));

        //Criando Dto do Autor
        AutorDto autorDto = new AutorDto();
        autorDto.setId(autor.getId());
        autorDto.setNome(autor.getNome());
        autorDto.setNacionalidade(autor.getNacionalidade());

        return autorDto;
    }


    // need to fix
    public AutorDto buscarAutorPorNome(String nome){
        Autor autor = autorRepo.findByNome(nome).orElseThrow(() -> new AutorNaoEncontradoException(nome));

        //Criando Dto do Autor
        AutorDto autorDto = new AutorDto();
        autorDto.setId(autor.getId());
        autorDto.setNome(autor.getNome());
        autorDto.setNacionalidade(autor.getNacionalidade());

        return autorDto;
    }

    public List<AutorDto> buscarTodosAutores(){
        List<Autor> autores = autorRepo.findAll();
        List<AutorDto> autoresDto = AutoresParaDto(autores);

        return autoresDto;
    }

    @Transactional
    public void atualizarAutorPorId(Long id, AutorDto autorDto){
        Autor autorDB = autorRepo.findById(id).orElseThrow(() -> new AutorNaoEncontradoException(id));


        if (autorDto.getNome() != null) {
            autorDB.setNome(autorDto.getNome());
        }

        if (autorDto.getNacionalidade() != null) {
            autorDB.setNacionalidade(autorDto.getNacionalidade());
        }


        autorRepo.save(autorDB);
    }


    @Transactional
    public void deletarAutorPorId(Long id){
        try{
            autorRepo.deleteById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }



    public List<AutorDto> AutoresParaDto(List<Autor> autores){
        List<AutorDto> autoresDto = autores.stream()
                .map(autor -> new AutorDto(
                        autor.getId(),
                        autor.getNome(),
                        autor.getNacionalidade()
                )).toList();



        return autoresDto;
    }
}
