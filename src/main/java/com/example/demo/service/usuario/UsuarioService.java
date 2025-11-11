package com.example.demo.service.usuario;

import com.example.demo.domain.model.dto.usuario.UsuarioDTO;
import com.example.demo.domain.model.usuario.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository repository;

    public List<Usuario> listarTodosUsuarios() {
        return repository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Não encontrado usuário com ID: " + id));
    }

    public void excluirUsuario(Long id){
        repository.deleteById(id);
    }



}
