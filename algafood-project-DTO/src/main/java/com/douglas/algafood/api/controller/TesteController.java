package com.douglas.algafood.api.controller;

import static com.douglas.algafood.infrastructure.repository.spec.RestauranteSpecs.comFreteGratis;
import static com.douglas.algafood.infrastructure.repository.spec.RestauranteSpecs.comNomeSemelhante;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douglas.algafood.domain.model.Cozinha;
import com.douglas.algafood.domain.model.Restaurante;
import com.douglas.algafood.domain.repository.CozinhaRepository;
import com.douglas.algafood.domain.repository.RestauranteRepository;
import com.douglas.algafood.domain.repository.RestauranteRepositoryQueries;
import com.douglas.algafood.infrastructure.repository.spec.RestauranteSpecs;

@RestController
@RequestMapping("/teste")
public class TesteController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@GetMapping("/cozinhas/por-nome")
	public List<Cozinha> cozinhasPorNome(String nome){
		return cozinhaRepository.findTodasByNome(nome);
	}
	
	@GetMapping("/cozinhas/todas-por-nome")
	public Optional<Cozinha> cozinhaPorNome(String nome) {
		return cozinhaRepository.findByNome(nome);
	}

	@GetMapping("/restaurantes/por-taxa")
	public List<Restaurante> restaurantesPorTaxa(BigDecimal taxaInicial, BigDecimal taxaFinal){
		return restauranteRepository.findByTaxaFreteBetween(taxaInicial,taxaFinal);
	}
	@GetMapping("/restaurantes/por-nome")
	public List<Restaurante> restaurantesPorNome(String nome, Long cozinhaId){
		return restauranteRepository.consultarPorNome(nome, cozinhaId);
	}
	
	@GetMapping("/restaurantes/top2-por-nome")
	public List<Restaurante> restaurantesTop2PorNome(String nome){
		return restauranteRepository.findTop2ByNomeContaining(nome);
	}
	
	@GetMapping("/restaurantes/por-nome-e-frete")
	public List<Restaurante> restaurantesPorNomeFrete(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
		return restauranteRepository.find(nome,taxaFreteInicial,taxaFreteFinal);
	}
	
	@GetMapping("/restaurantes/count-por-cozinha")
	public int restaurantesCountPorCozinha(Long cozinhaId){
		return restauranteRepository.countByCozinhaId(cozinhaId);
	}

	@GetMapping("/cozinhas/existe-nome")
	public boolean ExistecozinhaPorNome(String nome) {
		return cozinhaRepository.existsByNome(nome);
	}
	
	@GetMapping("/restaurantes/com-frete-gratis")
	public List<Restaurante> restaurantesComFreteGratis(String nome){
		return restauranteRepository.findComFreteGratis(nome);
	}
	
	@GetMapping("/restaurantes/primeiro")
	public Optional<Restaurante> restaurantePrimeiro(){
		return restauranteRepository.buscarPrimeiro();
	}
}