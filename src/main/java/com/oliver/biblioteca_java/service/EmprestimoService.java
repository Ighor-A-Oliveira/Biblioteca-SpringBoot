package com.oliver.biblioteca_java.service;


import com.oliver.biblioteca_java.dto.EmprestimoDto;
import com.oliver.biblioteca_java.dto.GeneroDto;
import com.oliver.biblioteca_java.entity.Emprestimo;
import com.oliver.biblioteca_java.entity.Genero;
import com.oliver.biblioteca_java.entity.Livro;
import com.oliver.biblioteca_java.entity.Membro;
import com.oliver.biblioteca_java.exception.*;
import com.oliver.biblioteca_java.repo.EmprestimoRepo;
import com.oliver.biblioteca_java.repo.LivroRepo;
import com.oliver.biblioteca_java.repo.MembroRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmprestimoService {

    private final EmprestimoRepo emprestimoRepo;
    private final LivroRepo livroRepo;
    private final MembroRepo membroRepo;

    public EmprestimoService(EmprestimoRepo emprestimoRepo, LivroRepo livroRepo, MembroRepo membroRepo){
        this.emprestimoRepo = emprestimoRepo;
        this.livroRepo = livroRepo;
        this.membroRepo = membroRepo;
    }

    @Transactional
    public void realizarEmprestimo(Long membroId, Long livroId){
        //Checando se temos o livro no acervo
        Livro livro = livroRepo.findById(livroId).orElseThrow(() -> new LivroNaoEncontradoException(livroId));

        //checando se temos o livro disponivel em estoque
        if(livro.getQntEstoque() <= 0){
            throw new LivroNaoDisponivelException(livroId);
        }

        //checando se temos o membro cadastrado
        Membro membro = membroRepo.findById(membroId).orElseThrow(() -> new MembroNaoEncontradoException(membroId));

        //Checando se o membro tem ainda tem emprestimos disponiveis
        if(membro.getQntEmprestimo() <= 0){
            throw new LimiteEmprestimosExcedidoException(membroId);
        }


        //checando se a conta esta ativa
        if(!membro.getAtivo()){
            throw new MembroInativoException(membroId);
        }

        //passou na checagem, a gente aceita o emprestimo, subtrai um do estoque e salva
        livro.setQntEstoque(livro.getQntEstoque()-1);
        livroRepo.save(livro);

        //Diminuindo o numero de emprestimos disponiveis  ao membro
        membro.setQntEmprestimo(membro.getQntEmprestimo()-1);
        membroRepo.save(membro);

        //Depois de atualizar o estoque a gente cria o emprestimo
        Emprestimo emp = new Emprestimo();
        emp.setMembro(membro);
        emp.setLivro(livro);
        emp.setDataEmprestimo(LocalDate.now());
        emp.setDataDevolucaoPrevista(LocalDate.now().plusDays(14));
        emp.setStatus("ATIVO");

        emprestimoRepo.save(emp);
    }

    @Transactional
    public void devolverLivro(Long emprestimoId){
        Emprestimo emp = emprestimoRepo.findById(emprestimoId).orElseThrow(() -> new EmprestimoNaoEncontradoException(emprestimoId));
        Membro membro = membroRepo.findById(emp.getMembro().getId()).orElseThrow(() -> new MembroNaoEncontradoException(emp.getMembro().getId()));

        Livro livro = emp.getLivro();
        livro.setQntEstoque(livro.getQntEstoque()+1);
        //Aumentado o numero de emprestimos disponiveis ao membro
        membro.setQntEmprestimo(membro.getQntEmprestimo()+1);
        livroRepo.save(livro);
        membroRepo.save(membro);

        emp.setDataDevolucaoReal(LocalDate.now());
        emp.setStatus("DEVOLVIDO");

        emprestimoRepo.save(emp);
    }

    public EmprestimoDto buscarPorId(Long id) {
        Emprestimo emp = emprestimoRepo.findById(id)
                .orElseThrow(() -> new EmprestimoNaoEncontradoException(id));

        EmprestimoDto empDto = new EmprestimoDto();
        empDto.setId(emp.getId());
        empDto.setDataEmprestimo(emp.getDataEmprestimo());
        empDto.setDataDevolucaoPrevista(emp.getDataDevolucaoPrevista());
        empDto.setDataDevolucaoReal(emp.getDataDevolucaoReal());
        empDto.setStatus(emp.getStatus());
        empDto.setLivroId(emp.getLivro().getId());
        empDto.setMembroId(emp.getMembro().getId());

        return empDto;
    }

    public List<EmprestimoDto> buscarTodosEmprestimos(){
        List<Emprestimo> emprestimos = emprestimoRepo.findAll();
        return emprestimosParaDtos(emprestimos);
    }


    public List<EmprestimoDto> emprestimosParaDtos(List<Emprestimo> emprestimos){
        return emprestimos.stream()
                .map(emprestimo -> new EmprestimoDto(
                        emprestimo.getId(),
                        emprestimo.getDataEmprestimo(),
                        emprestimo.getDataDevolucaoPrevista(),
                        emprestimo.getDataDevolucaoReal(),
                        emprestimo.getStatus(),
                        emprestimo.getLivro().getId(),
                        emprestimo.getMembro().getId()
                ))
                .toList();
    }
}
