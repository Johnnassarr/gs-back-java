package com.example.demo.service.tarefa;

import com.example.demo.domain.model.dto.tarefa.CategoriaSustentabilidadeDTO;
import com.example.demo.domain.model.tarefa.CategoriaSustentabilidade;
import com.example.demo.repository.CategoriaSustentabilidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaSustentabilidadeService {

    private final CategoriaSustentabilidadeRepository repository;

    public CategoriaSustentabilidadeService(CategoriaSustentabilidadeRepository repository) {
        this.repository = repository;
    }

    public List<CategoriaSustentabilidade> listarTodasCategorias() {
        return repository.findAll();
    }

    public CategoriaSustentabilidade criarCategoria(CategoriaSustentabilidadeDTO dto) {
        CategoriaSustentabilidade categoria = new CategoriaSustentabilidade();
        categoria.setNome(dto.nome());
        categoria.setDescricao(dto.descricao());
        categoria.setNivelImpacto(dto.nivelImpacto());
        return repository.save(categoria);
    }

    public CategoriaSustentabilidade atualizarCategoria(Long id, CategoriaSustentabilidadeDTO dto) {
        CategoriaSustentabilidade categoria = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        categoria.setNome(dto.nome());
        categoria.setDescricao(dto.descricao());
        categoria.setNivelImpacto(dto.nivelImpacto());
        return repository.save(categoria);
    }

    public void deletarCategoria(Long id) {
        repository.deleteById(id);
    }
}
