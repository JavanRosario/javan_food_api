package com.javanfood.javanfood.api.controlers;


import com.javanfood.javanfood.api.model.CozinhaXml;
import com.javanfood.javanfood.domain.model.Cozinha;
import com.javanfood.javanfood.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaApi {

    @Autowired
    private CozinhaRepository cozinhaRepository;


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
       return cozinhaRepository.adicionar(cozinha);
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
