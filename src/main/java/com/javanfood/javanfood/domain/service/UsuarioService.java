package com.javanfood.javanfood.domain.service;

import com.javanfood.javanfood.api.dto.request.UsuarioSemSenhaRequest;
import com.javanfood.javanfood.api.mapper.usuarioMapper.UsuarioRequestMapper;
import com.javanfood.javanfood.domain.exception.NegocioException;
import com.javanfood.javanfood.domain.exception.UsuarioNaoEncontradoException;
import com.javanfood.javanfood.domain.model.Usuario;
import com.javanfood.javanfood.domain.repository.UsuarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioRequestMapper usuarioRequestMapper;
    private final EntityManager entityManager;


    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarOuFalha(Long usuarioId) {
        return usuarioRepository.findById(usuarioId).orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
    }

    @Transactional
    public Usuario atualizar(Long id, UsuarioSemSenhaRequest usuarioSemSenhaRequest) {
        Usuario usuario = buscarOuFalha(id);
        usuarioRequestMapper.updateEntityFromDto(usuarioSemSenhaRequest, usuario);
        return salvar(usuario);
    }

    @Transactional
    public Usuario salvar(Usuario usuario) {
        entityManager.detach(usuario);
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());

        if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
            throw new NegocioException(String.format("Já existe cadastro com esse email: %s", usuario.getEmail()));
        }
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void mudarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
        Usuario usuario = buscarOuFalha(usuarioId);

        if (!usuario.getSenha().equals(senhaAtual)) {
            throw new NegocioException("Senha atual incorreta");
        }

        usuario.setSenha(novaSenha);
        usuarioRepository.save(usuario);
    }
}

