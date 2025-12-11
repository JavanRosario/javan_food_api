package com.javanfood.javanfood.api.controler;

import com.javanfood.javanfood.api.repository.CidadeRepository;
import com.javanfood.javanfood.api.service.CadastroCidadeService;
import com.javanfood.javanfood.domain.exeption.EntidadeEmUsoExeption;
import com.javanfood.javanfood.domain.exeption.EntidadeNaoEncontradaExeption;
import com.javanfood.javanfood.domain.model.Cidade;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeControler {

    @Autowired
    CidadeRepository cidadeRepository;

    @Autowired
    CadastroCidadeService cadastroCidadeService;

    @GetMapping
    public List<Cidade> listar(Cidade cidade) {
        return cidadeRepository.listar();
    }

    @GetMapping("/{cidadeId}")
    public ResponseEntity<Cidade> listarId(@PathVariable Long cidadeId) {
        Cidade cidade = cidadeRepository.findById(cidadeId);

        if (cidadeId != null) {
            return ResponseEntity.ok(cidade);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Cidade cidade) {
        try {
            cidade = cadastroCidadeService.salvar(cidade);
            return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
        } catch (EntidadeNaoEncontradaExeption e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{cidadeId}")
    public ResponseEntity<?> atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {

        try {
            Cidade cidadeAtual = cidadeRepository.findById(cidadeId);

            if (cidadeAtual != null) {
                BeanUtils.copyProperties(cidade, cidadeAtual, "id");
                cidadeAtual = cadastroCidadeService.salvar(cidadeAtual);

                return ResponseEntity.ok(cidadeAtual);
            }

            return ResponseEntity.notFound().build();
        } catch (EntidadeNaoEncontradaExeption e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{cidadeId}")
    public ResponseEntity<Cidade> delete(@PathVariable Long cidadeId) {
        try {
            cadastroCidadeService.excluir(cidadeId);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaExeption e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoExeption e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
