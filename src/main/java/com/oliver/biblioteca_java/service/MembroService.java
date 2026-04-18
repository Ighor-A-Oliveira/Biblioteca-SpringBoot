package com.oliver.biblioteca_java.service;

import com.oliver.biblioteca_java.dto.MembroDto;
import com.oliver.biblioteca_java.entity.Emprestimo;
import com.oliver.biblioteca_java.entity.Membro;
import com.oliver.biblioteca_java.repo.MembroRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MembroService {

    private final MembroRepo membroRepo;

    public MembroService(MembroRepo membroRepo){
        this.membroRepo = membroRepo;
    }


    @Transactional
    public void salvarMembro(MembroDto membroDto){
        Membro membro = new Membro();
        membro.setNome(membroDto.getNome());
        membro.setEmail(membroDto.getEmail());
        membro.setTelefone(membroDto.getTelefone());
        membro.setQntEmprestimo(3);
        membro.setDataCadastro(LocalDate.now());
        membro.setAtivo(true);
        membroRepo.save(membro);
    }

    public MembroDto buscarMembroPorId(Long id){
        //encontrando a entidade livro
        Membro membro = membroRepo.findById(id).orElseThrow(() -> new RuntimeException("Membro nao encontrado"));

        //Criando Dto do livro
        MembroDto membroDto = new MembroDto();
        //Populando os campos necessarios para um busca simples
        membroDto.setId(membro.getId());
        membroDto.setNome(membro.getNome());
        membroDto.setEmail(membro.getEmail());
        membroDto.setTelefone(membro.getTelefone());
        membroDto.setDataCadastro(membro.getDataCadastro());
        membroDto.setAtivo(membro.getAtivo());

        return membroDto;
    }

    //need to fix, it is not working properly
    public List<MembroDto> buscarMembroPorNome(String nome){
        //encontrando a entidade livro
        List<Membro> membros = membroRepo.findByNome(nome);

        if (membros.isEmpty()) {
            throw new RuntimeException("Genero não encontrado");
        }

        //Convertendo List<Genero> para List<GeneroDto>
        List<MembroDto> membrosDto = membrosParaDtos(membros);

        return membrosDto;
    }


    public List<MembroDto> buscarTodosMembro(){
        List<Membro> membros = membroRepo.findAll();
        List<MembroDto> membrosDtos = membrosParaDtos(membros);

        return membrosDtos;
    }

    @Transactional
    public void atualizarMembroPorId(Long id, MembroDto membroDto){
        Membro membroDB = membroRepo.findById(id).orElseThrow(() -> new RuntimeException("Membro náo encontrado"));

        //checando se o dto tem certos campos preenchidos
        //se esta preenchido a gente atualiza o registro da entidade original com os dados vindos do dto

        if (membroDto.getNome() != null) {
            membroDB.setNome(membroDto.getNome());
        }

        if (membroDto.getEmail() != null) {
            membroDB.setEmail(membroDto.getEmail());
        }

        if (membroDto.getTelefone() != null) {
            membroDB.setTelefone(membroDto.getTelefone());
        }

        if (membroDto.getDataCadastro() != null) {
            membroDB.setDataCadastro(membroDto.getDataCadastro());
        }

        if (membroDto.getAtivo() != null) {
            membroDB.setAtivo(membroDto.getAtivo());
        }

        membroRepo.save(membroDB);
    }


    @Transactional
    public void deletarMembroPorId(Long id){
        try{
            membroRepo.deleteById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public List<MembroDto> membrosParaDtos(List<Membro> membros){
        return membros.stream()
                .map(membro -> new MembroDto(
                        membro.getId(),
                        membro.getNome(),
                        membro.getEmail(),
                        membro.getTelefone(),
                        membro.getQntEmprestimo(),
                        membro.getDataCadastro(),
                        membro.getAtivo(),
                        membro.getEmprestimos() == null
                                //se for null retorna uma lista vazia
                                ? List.of()
                                //se nao retorna uma lista com os ids dos emprestimos desse membro
                                : membro.getEmprestimos().stream()
                                .map(Emprestimo::getId)
                                .toList()
                ))
                .toList();
    }
}
