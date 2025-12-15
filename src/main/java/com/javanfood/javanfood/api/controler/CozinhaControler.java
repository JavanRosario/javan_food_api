package com.javanfood.javanfood.api.controler;


import com.javanfood.javanfood.domain.repository.CozinhaRepository;
import com.javanfood.javanfood.domain.service.CadastroCozinhaService;
import com.javanfood.javanfood.domain.exeption.EntidadeEmUsoExeption;
import com.javanfood.javanfood.domain.exeption.EntidadeNaoEncontradaExeption;
import com.javanfood.javanfood.domain.model.Cozinha;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaControler {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;


    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    @GetMapping("/{cozinha_id}")
    public ResponseEntity<Cozinha> listarId(@PathVariable Long cozinha_id) {
        Optional<Cozinha> cozinha = cozinhaRepository.findById(cozinha_id);

        if (cozinha.isPresent()) {
            return ResponseEntity.ok(cozinha.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> adicionar(@RequestBody Cozinha cozinha) {
        try {
            Cozinha cozinhaSalva = cadastroCozinhaService.salvar(cozinha);
            return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaSalva);
        } catch (EntidadeNaoEncontradaExeption e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/{cozinha_id}")
    public ResponseEntity<?> atualizar(@PathVariable Long cozinha_id, @RequestBody Cozinha cozinha) {
        try {
            Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(cozinha_id);

            if (cozinhaAtual.isPresent()) {
                BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");

                Cozinha cozinhaSalva = cadastroCozinhaService.salvar(cozinhaAtual.get());
                return ResponseEntity.ok(cozinhaSalva);
            }

            return ResponseEntity.notFound().build();
        }catch (EntidadeNaoEncontradaExeption e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping("/{cozinha_id}")
    public ResponseEntity<?> delete(@PathVariable Long cozinha_id) {

        try {
            cadastroCozinhaService.excluir(cozinha_id);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaExeption e) {
            return ResponseEntity.notFound().build();

        } catch (EntidadeEmUsoExeption e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }


}
