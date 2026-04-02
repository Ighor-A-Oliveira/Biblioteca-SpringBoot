package com.oliver.biblioteca_java.controller;

import com.oliver.biblioteca_java.dto.LivroDto;
import com.oliver.biblioteca_java.entity.Livro;
import com.oliver.biblioteca_java.repo.LivroRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroController {

    //criar metodo para mapear de Entidade para dto e viceversa
    //Separar metodo de comparacao em update e apenas chamar qnd necessario passando os objetos
    //Add buscar por autor, por genero e por ano de publicacao

    private final LivroRepo livroRepo;


    public LivroController(LivroRepo livroRepo) {
        this.livroRepo = livroRepo;
    }

    public void salvarLivro(LivroDto livroDto){
        Livro livro = new Livro();
        livro.setIsbn(livroDto.getIsbn());
        livro.setAutores(livroDto.getAutores());// falta tratar autores um pouco mais
        livro.setGenero(livroDto.getGenero());
        livro.setTitulo(livroDto.getTitulo());
        livro.setAnoPublicacao(livroDto.getAnoPublicacao());
        livro.setQntEstaque(livroDto.getQntEstaque());

        livroRepo.saveAndFlush(livro);
    }

    public LivroDto buscarLivroPorId(Long id){
        Livro livro = livroRepo.findById(id).orElseThrow(() -> new RuntimeException("Livro nao encontrado"));
        LivroDto livroDto = new LivroDto();
        livroDto.setIsbn(livro.getIsbn());
        livroDto.setAutores(livro.getAutores());// falta tratar autores um pouco mais
        livroDto.setGenero(livro.getGenero());
        livroDto.setTitulo(livro.getTitulo());
        livroDto.setAnoPublicacao(livro.getAnoPublicacao());
        livroDto.setQntEstaque(livro.getQntEstaque());

        return livroDto;
    }

    public LivroDto buscarLivroPorIsbn(String isbn){
        Livro livro = livroRepo.findByIsbn(isbn).orElseThrow(() -> new RuntimeException("Livro nao encontrado"));
        LivroDto livroDto = new LivroDto();
        livroDto.setIsbn(livro.getIsbn());
        livroDto.setAutores(livro.getAutores());// falta tratar autores um pouco mais
        livroDto.setGenero(livro.getGenero());
        livroDto.setTitulo(livro.getTitulo());
        livroDto.setAnoPublicacao(livro.getAnoPublicacao());
        livroDto.setQntEstaque(livro.getQntEstaque());

        return livroDto;
    }

    public LivroDto buscarUsuarioPorTitulo(String titulo){
        Livro livro = livroRepo.findByTitulo(titulo).orElseThrow(() -> new RuntimeException("Livro nao encontrado"));
        LivroDto livroDto = new LivroDto();
        livroDto.setIsbn(livro.getIsbn());
        livroDto.setAutores(livro.getAutores());// falta tratar autores um pouco mais
        livroDto.setGenero(livro.getGenero());
        livroDto.setTitulo(livro.getTitulo());
        livroDto.setAnoPublicacao(livro.getAnoPublicacao());
        livroDto.setQntEstaque(livro.getQntEstaque());

        return livroDto;
    }

    public List<LivroDto> buscarTodosLivros(){
        List<Livro> livros = livroRepo.findAll();
        List<LivroDto> livrosDtos = livros.stream()
                .map(livro -> new LivroDto(
                        livro.getId(),
                        livro.getTitulo(),
                        livro.getIsbn(),
                        livro.getGenero(),
                        livro.getAnoPublicacao(),
                        livro.getQntEstaque()
                ))
                .toList();

        return livrosDtos;
    }

    public void atualizarLivroPorId(Long id, LivroDto livroDto){
        Livro livroDB = livroRepo.findById(id).orElseThrow(() -> new RuntimeException("Livro náo encontrado"));
        Livro livroNew = Livro.builder()
                .autores(livroDto.getAutores() != null ? livroDto.getAutores() : livroDB.getAutores())
                .genero(livroDto.getGenero() != null ? livroDto.getGenero() : livroDB.getGenero())
                .titulo(livroDto.getTitulo() != null ? livroDto.getTitulo() : livroDB.getTitulo())
                .isbn(livroDto.getIsbn() != null ? livroDto.getIsbn() : livroDB.getIsbn())
                .anoPublicacao(livroDto.getAnoPublicacao() != 0 ? livroDto.getAnoPublicacao() : livroDB.getAnoPublicacao())
                .qntEstaque(livroDto.getQntEstaque() > 0 ? livroDto.getQntEstaque() : livroDB.getQntEstaque())
                .build();
        livroRepo.saveAndFlush(livroNew);
    }

    public void atualizarLivroPorIsbn(String isbn, LivroDto livroDto){
        Livro livroDB = livroRepo.findByIsbn(isbn).orElseThrow(() -> new RuntimeException("Livro náo encontrado"));
        Livro livroNew = Livro.builder()
                .autores(livroDto.getAutores() != null ? livroDto.getAutores() : livroDB.getAutores())
                .genero(livroDto.getGenero() != null ? livroDto.getGenero() : livroDB.getGenero())
                .titulo(livroDto.getTitulo() != null ? livroDto.getTitulo() : livroDB.getTitulo())
                .isbn(livroDto.getIsbn() != null ? livroDto.getIsbn() : livroDB.getIsbn())
                .anoPublicacao(livroDto.getAnoPublicacao() != 0 ? livroDto.getAnoPublicacao() : livroDB.getAnoPublicacao())
                .qntEstaque(livroDto.getQntEstaque() > 0 ? livroDto.getQntEstaque() : livroDB.getQntEstaque())
                .build();
        livroRepo.saveAndFlush(livroNew);
    }

    public void deletarPorId(Long id){
        Livro livroDB = livroRepo.findById(id).orElseThrow(() -> new RuntimeException("Livro náo encontrado"));
        try{
            livroRepo.deleteById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletarPorIsbn(String isbn){
        Livro livroDB = livroRepo.findByIsbn(isbn).orElseThrow(() -> new RuntimeException("Livro náo encontrado"));
        try{
            livroRepo.deleteByIsbn(isbn);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
