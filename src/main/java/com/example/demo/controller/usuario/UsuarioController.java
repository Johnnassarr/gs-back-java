package com.example.demo.controller.usuario;

import com.example.demo.domain.model.dto.usuario.UsuarioDTO;
import com.example.demo.domain.model.usuario.Usuario;
import com.example.demo.service.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    UsuarioService service;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        List<Usuario> usuarios = service.listarTodosUsuarios();
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Usuario usuario = service.buscarPorId(id);
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encotrado usuário com ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        try {
            service.excluirUsuario(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuário excluido ID: " + id);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encotrado usuário com ID: " + id);
        }
    }


}
