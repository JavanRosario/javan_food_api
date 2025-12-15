package com.javanfood.javanfood.api.controler;

import com.javanfood.javanfood.domain.model.Cozinha;
import com.javanfood.javanfood.domain.model.Restaurante;
import com.javanfood.javanfood.domain.repository.CozinhaRepository;
import com.javanfood.javanfood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teste")
public class TesteControler {
    @Autowired
    public CozinhaRepository cozinhaRepository;

    @Autowired
    public RestauranteRepository restauranteRepository;

    @GetMapping("/cozinhas/por-nome")
    public List<Cozinha> cozinhasPorNome(@RequestParam("nome") String nome) {
        return cozinhaRepository.findByNomeContaining(nome);
    }

    @GetMapping("/cozinhas/unico-por-nome")
    public Optional<Cozinha> cozinhaPorNome(@RequestParam("nome") String nome) {
        return cozinhaRepository.findByNome(nome);
    }

    @GetMapping("/restaurantes/por-taxa")
    public List<Restaurante> restaurantePorTaxa(BigDecimal txInicial, BigDecimal txFinal) {
        return restauranteRepository.findByTaxaFreteBetween(txInicial, txFinal);
    }

    @GetMapping("/restaurantes/por-nome")
    public List<Restaurante> restaurantePorTaxa(String nome, Long id) {
        return restauranteRepository.findByNomeContainingAndCozinhaId(nome, id);
    }

    @GetMapping("/restaurantes/primeiro-por-nome")
    public Optional<Restaurante> restaurantePorUnicoNome(String nome) {
        return restauranteRepository.findFirstByNomeContaining(nome);
    }

    @GetMapping("/restaurantes/primeiro-por-nome-2")
    public List<Restaurante> restaurantePorUnicoNomePegandoDois(String nome) {
        return restauranteRepository.findTop2ByNomeContaining(nome);
    }

    @GetMapping("/restaurantes/exists")
    public boolean restauranteExists(String nome) {
        return restauranteRepository.existsByNome(nome);
    }

    @GetMapping("/restaurantes/count")
    public int restauranteExists(Long cozinhaId) {
        return restauranteRepository.countByCozinhaId(cozinhaId);
    }

    @GetMapping("/restaurantes/por-nome-id")
    public List<Restaurante> cozinhasPorNomeId(@RequestParam String nome, @RequestParam Long cozinhaId) {
        return restauranteRepository.consultarPorNome(nome, cozinhaId);
    }

    @GetMapping("restaurantes/por-nome-e-frete")
    public List<Restaurante> restaurantesPorNomeFrete(@RequestParam String nome,
                                                      @RequestParam BigDecimal txFreteInicial,
                                                      @RequestParam BigDecimal txFreteFinal) {
        return restauranteRepository.find(nome, txFreteInicial, txFreteFinal);

    }

}
