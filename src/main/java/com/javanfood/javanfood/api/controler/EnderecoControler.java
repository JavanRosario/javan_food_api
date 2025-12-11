package com.javanfood.javanfood.api.controler;

import com.javanfood.javanfood.api.repository.EstadoRespository;
import com.javanfood.javanfood.api.service.CadastroEstadoService;
import com.javanfood.javanfood.domain.exeption.EntidadeEmUsoExeption;
import com.javanfood.javanfood.domain.exeption.EntidadeNaoEncontradaExeption;
import com.javanfood.javanfood.domain.model.Estado;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoControler {

    @Autowired
    private EstadoRespository estadoRespository;

    @Autowired
    private CadastroEstadoService cadastroEstadoService;

    @GetMapping
    public List<Estado> listar() {
        return estadoRespository.listar();
    }

    @GetMapping("/{enderecoId}")
    public ResponseEntity<Estado> listarid(@PathVariable Long enderecoId) {
        Estado estado = estadoRespository.findById(enderecoId);

        if (estado != null) {
            return ResponseEntity.ok(estado);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Estado estado) {
        try {
            estado = cadastroEstadoService.salvar(estado);
            return ResponseEntity.status(HttpStatus.CREATED).body(estado);
        } catch (EntidadeNaoEncontradaExeption e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{enderecoId}")
    public ResponseEntity<?> atualizar(@PathVariable Long enderecoId, @RequestBody Estado estado) {
        try {
            Estado estadoAtual = estadoRespository.findById(enderecoId);

            if (estadoAtual != null) {
                BeanUtils.copyProperties(estado, estadoAtual, "id");
                estadoAtual = cadastroEstadoService.salvar(estadoAtual);

                return ResponseEntity.ok(estadoAtual);
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
