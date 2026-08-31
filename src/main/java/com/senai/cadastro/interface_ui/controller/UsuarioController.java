package com.senai.cadastro.interface_ui.controller;

import com.senai.cadastro.domain.repository.UsuarioRepositry;
import com.senai.cadastro.domain.entity.Usuario;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    final UsuarioRepositry usuarioRepositry;

    @GetMapping
    public List<Usuario> listarTodosUsuarios() {
        return usuarioRepositry.findAll();
    }

    @GetMapping("/{id}")
    public Usuario buscarUsuarioporId(@PathVariable UUID id) {

        Optional<Usuario> usuarioOpt = usuarioRepositry.findById(id);

        if (usuarioOpt.isPresent()){
            return usuarioOpt.get();
        }
        else {
            throw  new RuntimeException("Usuario nao encontrado ");
        }
    }

    @PostMapping
    public Usuario cadastrarUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioRepositry.save(usuario);

    }

    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@PathVariable UUID id, @Valid @RequestBody Usuario usuario) {
      Usuario usuarioExistente = buscarUsuarioporId(id);
      usuarioExistente.setNome(usuario.getNome());
      usuarioExistente.setCpf(usuario.getCpf());
      usuarioExistente.setEmail(usuarioExistente.getEmail());
        return usuarioExistente;
    }
    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable UUID id) {
        usuarioRepositry.deleteById(id);
    }
}
