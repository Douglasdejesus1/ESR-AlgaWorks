package com.douglas.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebInputException;

import com.douglas.algafood.domain.exception.EntidadeEmUsoException;
import com.douglas.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.douglas.algafood.domain.model.Cozinha;
import com.douglas.algafood.domain.repository.CozinhaRepository;
import com.douglas.algafood.domain.service.CadastroCozinhaService;

//@Controller
//@ResponseBody
//substitui o responseBody e Controller
@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CadastroCozinhaService cadastroCozinha;

	@Autowired
	private CozinhaRepository cozinhaRepository;

	// GET listar
	@GetMapping
	public List<Cozinha> listar() {
		return cozinhaRepository.findAll();
	}

	//@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	//public CozinhasXmlWrapper listarXml() {
	//	return new CozinhasXmlWrapper(cozinhaRepository.listar());
	//}
	// @ResponseStatus(HttpStatus.CREATED)
	/*
	 * @GetMapping("/{cozinhaId}") public Cozinha buscar(@PathVariable("cozinhaId")
	 * Long id) { return cozinhaRepository.buscar(id); }
	 */

	// REFATORADO COM ResponseEntity
	/*
	 * @GetMapping("/{cozinhaId}") public ResponseEntity<Cozinha>
	 * buscar(@PathVariable("cozinhaId") Long cozinhaId) { Cozinha cozinha =
	 * cozinhaRepository.buscar(cozinhaId); return
	 * ResponseEntity.status(HttpStatus.OK).body(cozinha); return
	 * ResponseEntity.ok(cozinha); HttpHeaders headers = new HttpHeaders();
	 * headers.add(HttpHeaders.LOCATION, "https://www.amazon.com"); return
	 * ResponseEntity.status(HttpStatus.FOUND).headers(headers).build(); }
	 */

	// GET buscar
	/*@GetMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> buscar(@PathVariable("cozinhaId") Long cozinhaId) {
		Optional<Cozinha> cozinha = cozinhaRepository.findById(cozinhaId);
		
		if (cozinha.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(cozinha.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}*/
	@GetMapping("/{cozinhaId}")
	public Cozinha buscar(@PathVariable("cozinhaId") Long cozinhaId) {
	return cadastroCozinha.buscarOuFalhar(cozinhaId);
	}

	// POST

	// POST SEM RETORNO
	/*
	 * @PostMapping public void adicionar(@RequestBody Cozinha cozinha) {
	 * cozinhaRepository.salvar(cozinha); }
	 */

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Cozinha adicionar(@RequestBody @Valid Cozinha cozinha) {
		return cadastroCozinha.salvar(cozinha);
	}

	// PUT
	@PutMapping("/{cozinhaId}")
	public Cozinha atualizar(@PathVariable  Long cozinhaId, 
			@RequestBody @Valid Cozinha cozinha) {
		Cozinha cozinhaAtual = cadastroCozinha.buscarOuFalhar(cozinhaId);
		
		BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");

		return cadastroCozinha.salvar(cozinhaAtual);
			

	}

	// DELETE
	/*@DeleteMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> remover(@PathVariable Long cozinhaId) {
		try {
			// Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
			cadastroCozinha.excluir(cozinhaId);
			return ResponseEntity.noContent().build();
			
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
			
		} catch (EntidadeNaoEncontradaException e) {

			return ResponseEntity.notFound().build();

		}
	}*/
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{cozinhaId}")
	public void remover(@PathVariable Long cozinhaId) {
			cadastroCozinha.excluir(cozinhaId);

	}
	
	/*@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{cozinhaId}")
	public void remover(@PathVariable Long cozinhaId) {
		try {
			cadastroCozinha.excluir(cozinhaId);
		}catch(EntidadeNaoEncontradaException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		//	throw new ServerWebInputException(e.getMessage());
		}
	}*/
	
	
}
