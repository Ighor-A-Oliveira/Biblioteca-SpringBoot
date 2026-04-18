package com.oliver.biblioteca_java.service;

import com.oliver.biblioteca_java.dto.GeneroDto;
import com.oliver.biblioteca_java.dto.LivroDto;
import com.oliver.biblioteca_java.entity.Autor;
import com.oliver.biblioteca_java.entity.Genero;
import com.oliver.biblioteca_java.entity.Livro;
import com.oliver.biblioteca_java.exception.GeneroNotFoundException;
import com.oliver.biblioteca_java.repo.AutorRepo;
import com.oliver.biblioteca_java.repo.GeneroRepo;
import com.oliver.biblioteca_java.repo.LivroRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeneroService {

    private final GeneroRepo generoRepo;

    public GeneroService(GeneroRepo generoRepo) {
        this.generoRepo = generoRepo;
    }

    @Transactional
    public void salvarGenero(GeneroDto generoDto){
        Genero genero = new Genero();
        genero.setNome(generoDto.getNome());
        genero.setDescricao(generoDto.getDescricao());
        generoRepo.save(genero);
    }

    public GeneroDto buscarGeneroPorId(Long id){
        //encontrando a entidade livro
        Genero genero = generoRepo.findById(id).orElseThrow(() -> new GeneroNotFoundException(id));

        //Criando Dto do livro
        GeneroDto generoDto = new GeneroDto();
        //Populando os campos necessarios para um busca simples
        generoDto.setId(genero.getId());
        generoDto.setNome(genero.getNome());
        generoDto.setDescricao(genero.getDescricao());

        return generoDto;
    }

    //need to fix, it is not working properly
    public List<GeneroDto> buscarGeneroPorNome(String nome){
        //encontrando a entidade livro
        List<Genero> generos = generoRepo.findByNome(nome);

        if (generos.isEmpty()) {
            throw new GeneroNotFoundException();
        }

        //Convertendo List<Genero> para List<GeneroDto>
        List<GeneroDto> generosDto = generosParaDtos(generos);

        return generosDto;
    }


    public List<GeneroDto> buscarTodosGeneros(){
        List<Genero> generos = generoRepo.findAll();

        if (generos.isEmpty()) {
            throw new GeneroNotFoundException();
        }

        List<GeneroDto> generosDtos = generosParaDtos(generos);

        return generosDtos;
    }

    @Transactional
    public void atualizarGeneroPorId(Long id, GeneroDto generoDto){
        Genero generoDB = generoRepo.findById(id).orElseThrow(() -> new GeneroNotFoundException(id));

        //checando se o dto tem certos campos preenchidos
        //se esta preenchido a gente atualiza o registro da entidade original com os dados vindos do dto

        if (generoDto.getDescricao() != null) {
            generoDB.setDescricao(generoDto.getDescricao());
        }

        if (generoDto.getNome() != null) {
            generoDB.setNome(generoDto.getNome());
        }

        generoRepo.save(generoDB);
    }


    @Transactional
    public void deletarGeneroPorId(Long id){
        Genero generoDB = generoRepo.findById(id).orElseThrow(() -> new GeneroNotFoundException(id));
        try{
            generoRepo.deleteById(id);
        } catch (GeneroNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<GeneroDto> generosParaDtos(List<Genero> generos){
        return generos.stream()
                .map(genero -> new GeneroDto(
                        genero.getId(),
                        genero.getNome(),
                        genero.getDescricao(),
                        genero.getLivros() == null
                                //se for null retorna uma lista vazia
                                ? List.of()
                                //se nao retorna uma lista com os ids dos livros desse genero
                                : genero.getLivros().stream()
                                .map(Livro::getId)
                                .toList()
                ))
                .toList();
    }
}
