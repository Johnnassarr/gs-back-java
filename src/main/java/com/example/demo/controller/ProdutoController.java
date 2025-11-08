package com.example.demo.controller;

import com.example.demo.domain.model.produto.Produto;
import com.example.demo.domain.model.dto.produto.ProdutoDTO;
import com.example.demo.repository.ProdutoRepository;
import com.example.demo.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    ProdutoRepository repository;

    @Autowired
    ProdutoService service;

    @GetMapping("/listar")
    public ResponseEntity<List<Produto>> listar() {
        List<Produto> listaDeTodos = service.listar();
        return ResponseEntity.status(HttpStatus.OK).body(listaDeTodos);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<Produto> adicionar(@RequestBody ProdutoDTO dto) {
        Produto produtoAdicionado = service.adicionar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoAdicionado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> editar(@PathVariable Long id, @RequestBody ProdutoDTO dto) {
        Produto produtoAtualizado = service.editar(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(produtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
