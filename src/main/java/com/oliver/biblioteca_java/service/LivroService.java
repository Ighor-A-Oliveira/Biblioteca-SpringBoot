package com.oliver.biblioteca_java.service;

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
import java.util.stream.Collectors;

@Service
public class LivroService {
    //criar metodo para mapear de Entidade para dto e viceversa
    //Separar metodo de comparacao em update e apenas chamar qnd necessario passando os objetos
    //Add buscar por autor, por genero e por ano de publicacao

    private final LivroRepo livroRepo;
    private final AutorRepo autorRepo;
    private final GeneroRepo generoRepo;


    public LivroService(LivroRepo livroRepo, AutorRepo autorRepo, GeneroRepo generoRepo) {
        this.livroRepo = livroRepo;
        this.autorRepo = autorRepo;
        this.generoRepo = generoRepo;
    }

    @Transactional
    public void salvarLivro(LivroDto livroDto){
        Livro livro = new Livro();
        List<Autor> autores = DtoParaAutor(livroDto.getAutoresId());
        Genero genero = generoRepo.findById(livroDto.getGeneroId()).orElseThrow(() -> new RuntimeException("Genero nao encontrado"));
        livro.setIsbn(livroDto.getIsbn());
        livro.setAutores(autores);
        livro.setGenero(genero);
        livro.setTitulo(livroDto.getTitulo());
        livro.setAnoPublicacao(livroDto.getAnoPublicacao());
        livro.setQntEstoque(livroDto.getQntEstoque());

        livroRepo.saveAndFlush(livro);
    }


    public LivroDto buscarLivroPorId(Long id){
        //encontrando a entidade livro
        Livro livro = livroRepo.findById(id).orElseThrow(() -> new RuntimeException("Livro nao encontrado"));
        //Aqui criamos uma lista de entidades autor para que possamos usar o metodo AutorParaDto
        List<Autor> tempAutores= livro.getAutores();
        //Esse metodo converte List<Autor> autores para List<Long> autoresId
        List<Long> autores = AutorParaDto(tempAutores);

        //Criando Dto do livro
        LivroDto livroDto = new LivroDto();
        livroDto.setId(livro.getId());
        livroDto.setIsbn(livro.getIsbn());
        //definindo a lista com os ids dos autores
        livroDto.setAutoresId(autores);
        //definindo a lista com o id do genero do livro
        livroDto.setGeneroId(livro.getGenero().getId());
        livroDto.setTitulo(livro.getTitulo());
        livroDto.setAnoPublicacao(livro.getAnoPublicacao());
        livroDto.setQntEstoque(livro.getQntEstoque());

        return livroDto;
    }

    public LivroDto buscarLivroPorIsbn(String isbn){
        Livro livro = livroRepo.findByIsbn(isbn).orElseThrow(() -> new RuntimeException("Livro nao encontrado"));
        //Aqui criamos uma lista de entidades autor para que possamos usar o metodo AutorParaDto
        List<Autor> tempAutores= livro.getAutores();
        //Esse metodo converte List<Autor> autores para List<Long> autoresId
        List<Long> autores = AutorParaDto(tempAutores);


        LivroDto livroDto = new LivroDto();
        livroDto.setId(livro.getId());
        livroDto.setIsbn(livro.getIsbn());
        //definindo a lista com os ids dos autores
        livroDto.setAutoresId(autores);
        //definindo a lista com o id do genero do livro
        livroDto.setGeneroId(livro.getGenero().getId());
        livroDto.setTitulo(livro.getTitulo());
        livroDto.setAnoPublicacao(livro.getAnoPublicacao());
        livroDto.setQntEstoque(livro.getQntEstoque());

        return livroDto;
    }

    public LivroDto buscarLivroPorTitulo(String titulo){
        Livro livro = livroRepo.findByTitulo(titulo).orElseThrow(() -> new RuntimeException("Livro nao encontrado"));
        //Aqui criamos uma lista de entidades autor para que possamos usar o metodo AutorParaDto
        List<Autor> tempAutores= livro.getAutores();
        //Esse metodo converte List<Autor> autores para List<Long> autoresId
        List<Long> autoresId = AutorParaDto(tempAutores);

        LivroDto livroDto = new LivroDto();
        livroDto.setId(livro.getId());
        livroDto.setIsbn(livro.getIsbn());
        //definindo a lista com os ids dos autores
        livroDto.setAutoresId(autoresId);
        //definindo a lista com o id do genero do livro
        livroDto.setGeneroId(livro.getGenero().getId());
        livroDto.setTitulo(livro.getTitulo());
        livroDto.setAnoPublicacao(livro.getAnoPublicacao());
        livroDto.setQntEstoque(livro.getQntEstoque());

        return livroDto;
    }

    public List<LivroDto> buscarTodosLivros(){
        List<Livro> livros = livroRepo.findAll();
        List<LivroDto> livrosDtos = livros.stream()
                .map(livro -> new LivroDto(
                        livro.getId(),
                        livro.getTitulo(),
                        livro.getIsbn(),
                        livro.getGenero().getId(),
                        livro.getAnoPublicacao(),
                        livro.getQntEstoque(),
                        AutorParaDto(livro.getAutores())
                ))
                .toList();

        return livrosDtos;
    }

    @Transactional
    public void atualizarLivroPorId(Long id, LivroDto livroDto){
        Livro livroDB = livroRepo.findById(id).orElseThrow(() -> new RuntimeException("Livro náo encontrado"));

        //checando se o dto tem certos campos preenchidos
        //se esta preenchido a gente atualiza o registro da entidade original com os dados vindos do dto

        if (livroDto.getTitulo() != null) {
            livroDB.setTitulo(livroDto.getTitulo());
        }

        if (livroDto.getIsbn() != null) {
            livroDB.setIsbn(livroDto.getIsbn());
        }

        if (livroDto.getAnoPublicacao() != 0) {
            livroDB.setAnoPublicacao(livroDto.getAnoPublicacao());
        }

        if (livroDto.getQntEstoque() > 0) {
            livroDB.setQntEstoque(livroDto.getQntEstoque());
        }

        if (livroDto.getGeneroId() != null) {
            Genero genero = generoRepo.findById(livroDto.getGeneroId())
                    .orElseThrow(() -> new GeneroNotFoundException(livroDto.getGeneroId()));
            livroDB.setGenero(genero);
        }

        if (livroDto.getAutoresId() != null) {
            List<Autor> autores = autorRepo.findAllById(livroDto.getAutoresId());

            if (autores.size() != livroDto.getAutoresId().size()) {
                throw new RuntimeException("Alguns autores não foram encontrados");
            }

            livroDB.setAutores(autores);
        }

        livroRepo.save(livroDB);
    }

    @Transactional
    public void atualizarLivroPorIsbn(String isbn, LivroDto livroDto){
        Livro livroDB = livroRepo.findByIsbn(isbn).orElseThrow(() -> new RuntimeException("Livro náo encontrado"));

        //checando se o dto tem certos campos preenchidos
        //se esta preenchido a gente atualiza o registro da entidade original com os dados vindos do dto

        if (livroDto.getTitulo() != null) {
            livroDB.setTitulo(livroDto.getTitulo());
        }

        if (livroDto.getIsbn() != null) {
            livroDB.setIsbn(livroDto.getIsbn());
        }

        if (livroDto.getAnoPublicacao() != 0) {
            livroDB.setAnoPublicacao(livroDto.getAnoPublicacao());
        }

        if (livroDto.getQntEstoque() > 0) {
            livroDB.setQntEstoque(livroDto.getQntEstoque());
        }

        if (livroDto.getGeneroId() != null) {
            Genero genero = generoRepo.findById(livroDto.getGeneroId())
                    .orElseThrow(() -> new GeneroNotFoundException(livroDto.getGeneroId()));
            livroDB.setGenero(genero);
        }

        if (livroDto.getAutoresId() != null) {
            List<Autor> autores = autorRepo.findAllById(livroDto.getAutoresId());

            if (autores.size() != livroDto.getAutoresId().size()) {
                throw new RuntimeException("Alguns autores não foram encontrados");
            }

            livroDB.setAutores(autores);
        }
        livroRepo.save(livroDB);
    }

    @Transactional
    public void deletarLivroPorId(Long id){
        Livro livroDB = livroRepo.findById(id).orElseThrow(() -> new RuntimeException("Livro náo encontrado"));
        try{
            livroRepo.deleteById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void deletarLivroPorIsbn(String isbn){
        Livro livroDB = livroRepo.findByIsbn(isbn).orElseThrow(() -> new RuntimeException("Livro náo encontrado"));
        try{
            livroRepo.deleteByIsbn(isbn);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Autor> DtoParaAutor(List<Long> autoresId){
        List<Autor> autores = autorRepo.findAllById(autoresId);
        return autores;
    }

    public List<Long> AutorParaDto(List<Autor> autores){
        List<Long> autoresId = new ArrayList<>();

        for (Autor autor : autores){
            if(autor != null && autor.getId() != null){
                autoresId.add(autor.getId());
            }
        }

        return autoresId;
    }
}
