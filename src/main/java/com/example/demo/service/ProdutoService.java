package com.example.demo.service;

import com.example.demo.domain.model.produto.Produto;
import com.example.demo.domain.model.dto.produto.ProdutoDTO;
import com.example.demo.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository repository;

    public List<Produto> listar() {
        return repository.findAll();
    }

    public Produto adicionar(ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setMarca(dto.getMarca());
        return repository.save(produto);
    }

    public Produto editar(Long id, ProdutoDTO dto) {
        Optional<Produto> produtoExistente = repository.findById(id);

        if (produtoExistente.isPresent()) {
            Produto produto = produtoExistente.get(); //pega o id
            produto.setNome(dto.getNome());
            produto.setMarca(dto.getMarca());
            return repository.save(produto);
        } else {
            throw new RuntimeException("Não existe produto com esse ID: " + id);
        }
    }

    public void excluir(Long id) {
        Optional<Produto> produtoExistente = repository.findById(id);

        if (produtoExistente.isPresent()) {
            Produto produto = produtoExistente.get();
            repository.delete(produto);
        } else {
            throw new RuntimeException("Não existe produto com este ID: " + id);
        }
    }

}
