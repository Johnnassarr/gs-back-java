package com.example.demo.service.tarefa;

import com.example.demo.domain.model.dto.tarefa.TarefaDTO;
import com.example.demo.domain.model.tarefa.CategoriaSustentabilidade;
import com.example.demo.domain.model.tarefa.Tarefa;
import com.example.demo.domain.model.usuario.Usuario;
import com.example.demo.repository.CategoriaSustentabilidadeRepository;
import com.example.demo.repository.TarefaRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    @Autowired
    TarefaRepository tarefaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    CategoriaSustentabilidadeRepository categoriaSustentabilidadeRepository;

    public List<Tarefa> listarTodasTarefas() {
        return tarefaRepository.findAll();
    }

    public Tarefa buscarTarefaPorId(Long id) {
        return tarefaRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada!"));
    }

    public Tarefa criarTarefa(TarefaDTO dto) {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(dto.titulo());
        tarefa.setDescricao(dto.descricao());
        tarefa.setCompletado(dto.completado());
        tarefa.setDataCriacao(dto.dataCriacao());
        tarefa.setPoints(dto.points());

        CategoriaSustentabilidade categoria = categoriaSustentabilidadeRepository.findById(dto.categoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        tarefa.setCategoria(categoria);

        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        tarefa.setUsuario(usuario);

        return tarefaRepository.save(tarefa);
    }

    public Tarefa atualizarTarefa(Long id, TarefaDTO dto) {
        Tarefa tarefa = buscarTarefaPorId(id);
        
        tarefa.setTitulo(dto.titulo());
        tarefa.setDescricao(dto.descricao());
        tarefa.setCompletado(dto.completado());
        tarefa.setDataCriacao(dto.dataCriacao());
        tarefa.setPoints(dto.points());

        if (dto.categoriaId() != null) {
            CategoriaSustentabilidade categoria = categoriaSustentabilidadeRepository.findById(dto.categoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
            tarefa.setCategoria(categoria);
        }

        if (dto.usuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            tarefa.setUsuario(usuario);
        }

        return tarefaRepository.save(tarefa);
    }

    public void deletarTarefa(Long id) {
        tarefaRepository.deleteById(id);
    }

}
