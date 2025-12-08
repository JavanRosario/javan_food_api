package com.javanfood.javanfood.api.controler;


import com.javanfood.javanfood.api.model.CozinhaXml;
import com.javanfood.javanfood.api.service.CadastroCozinhaService;
import com.javanfood.javanfood.domain.exeption.EntidadeEmUsoExeption;
import com.javanfood.javanfood.domain.exeption.EntidadeNãoEncontradaExeption;
import com.javanfood.javanfood.domain.model.Cozinha;
import com.javanfood.javanfood.api.repository.CozinhaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaControler {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cozinha> listar() {
        System.out.println("LISTAR 1");
        return cozinhaRepository.listar();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhaXml listarXml() {
        return new CozinhaXml(cozinhaRepository.listar());
    }

    @GetMapping("/{cozinha_id}")
    public ResponseEntity<Cozinha> listarId(@PathVariable Long cozinha_id) {
        Cozinha cozinha = cozinhaRepository.findById(cozinha_id);

        if (cozinha != null) {
            return ResponseEntity.ok(cozinha);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody Cozinha cozinha) {
        return cadastroCozinhaService.salvar(cozinha);
    }

    @PutMapping("/{cozinha_id}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinha_id, @RequestBody Cozinha cozinha) {
        Cozinha cozinhaAtual = cozinhaRepository.findById(cozinha_id);

        if (cozinhaAtual != null) {
            BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");

            cozinhaAtual = cozinhaRepository.adicionar(cozinhaAtual);
            return ResponseEntity.ok(cozinhaAtual);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{cozinha_id}")
    public ResponseEntity<Cozinha> delete(@PathVariable Long cozinha_id) {

        try {

            cadastroCozinhaService.excluir(cozinha_id);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNãoEncontradaExeption e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoExeption e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }


    //    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
//    public List<Cozinha> listar2() {
//        System.out.println("LISTAR 2");
//        return cozinhaRepository.listar();
//    }


    //    @ResponseStatus(HttpStatus.CREATED)
//    @GetMapping("/{cozinhaId}")
//    public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
//        Cozinha cozinha = cozinhaRepository.findById(cozinhaId);
//
////        return ResponseEntity.status(HttpStatus.OK).build();
//
////        return ResponseEntity.ok(cozinha);
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add(HttpHeaders.LOCATION, "http://localhost:8080/cozinhas");
//
//        return ResponseEntity
//                .status(HttpStatus.FOUND)
//                .headers(httpHeaders)
//                .build();
//    }
}
