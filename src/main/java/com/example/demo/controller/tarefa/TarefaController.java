package com.example.demo.controller.tarefa;

import com.example.demo.domain.model.dto.tarefa.TarefaDTO;
import com.example.demo.domain.model.tarefa.Tarefa;
import com.example.demo.service.tarefa.TarefaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService service;

    public TarefaController(TarefaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Tarefa> listarTodas() {
        return service.listarTodasTarefas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarTarefaPorId(id));
    }

    @PostMapping
    public ResponseEntity<Tarefa> criar(@RequestBody TarefaDTO dto) {
        return ResponseEntity.ok(service.criarTarefa(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @RequestBody TarefaDTO dto) {
        return ResponseEntity.ok(service.atualizarTarefa(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletarTarefa(id);
        return ResponseEntity.noContent().build();
    }
}