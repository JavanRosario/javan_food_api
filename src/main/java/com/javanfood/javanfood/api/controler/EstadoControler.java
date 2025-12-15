package com.javanfood.javanfood.api.controler;

import com.javanfood.javanfood.domain.repository.EstadoRespository;
import com.javanfood.javanfood.domain.service.CadastroEstadoService;
import com.javanfood.javanfood.domain.exeption.EntidadeEmUsoExeption;
import com.javanfood.javanfood.domain.exeption.EntidadeNaoEncontradaExeption;
import com.javanfood.javanfood.domain.model.Estado;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enderecos")
public class EstadoControler {

    @Autowired
    private EstadoRespository estadoRespository;

    @Autowired
    private CadastroEstadoService cadastroEstadoService;

    @GetMapping
    public List<Estado> listar() {
        return estadoRespository.findAll();
    }

    @GetMapping("/{enderecoId}")
    public ResponseEntity<Estado> listarid(@PathVariable Long enderecoId) {
        Optional<Estado> estado = estadoRespository.findById(enderecoId);

        if (estado.isPresent()) {
            return ResponseEntity.ok(estado.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Estado estado) {

        try {
            Estado estadoAtual = cadastroEstadoService.salvar(estado);
            return ResponseEntity.status(HttpStatus.CREATED).body(estadoAtual);
        } catch (EntidadeNaoEncontradaExeption e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/{enderecoId}")
    public ResponseEntity<?> atualizar(@PathVariable Long enderecoId, @RequestBody Estado estado) {
        try {
            Optional<Estado> estadoAtual = estadoRespository.findById(enderecoId);

            if (estadoAtual.isPresent()) {
                BeanUtils.copyProperties(estado, estadoAtual.get(), "id");
                Estado estadoSalvo = cadastroEstadoService.salvar(estadoAtual.get());

                return ResponseEntity.ok(estadoSalvo);
            }

            return ResponseEntity.notFound().build();
        } catch (EntidadeNaoEncontradaExeption e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping("/{enderecoId}")
    public ResponseEntity<?> delete(@PathVariable Long enderecoId) {

        try {
            cadastroEstadoService.excluir(enderecoId);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaExeption e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoExeption e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

}
