package com.example.algamoney.api.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.example.algamoney.api.model.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoney.api.event.RecursoCriadoEvent;
import com.example.algamoney.api.model.Bateria;
import com.example.algamoney.api.repository.BateriaRepository;
import com.example.algamoney.api.service.BateriaService;

@RestController
@RequestMapping("/baterias")
public class BateriaResource {

	@Autowired
	private BateriaRepository bateriaRepository;

	@Autowired
	private BateriaService bateriaService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
	public ResponseEntity<Bateria> criar(@Valid @RequestBody Bateria bateria, HttpServletResponse response) {
		Bateria bateriaSalva = bateriaRepository.save(bateria);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, bateriaSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(bateriaSalva);
	}

	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and #oauth2.hasScope('read')")
	public ResponseEntity<Bateria> buscarPeloCodigo(@PathVariable Long codigo) {
		Bateria bateria = bateriaRepository.findOne(codigo);
		return bateria != null ? ResponseEntity.ok(bateria) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_PESSOA') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		bateriaRepository.delete(codigo);
	}

	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
	public ResponseEntity<Bateria> atualizar(@PathVariable Long codigo, @Valid @RequestBody Bateria bateria) {
		Bateria bateriaSalva = bateriaService.atualizar(codigo, bateria);
		return ResponseEntity.ok(bateriaSalva);
	}

	@GetMapping("/pesquisar")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA')")
	public Page<Bateria> pesquisarNome(@RequestParam(required = false, defaultValue = "%") String nome, Pageable pageable) {
		return bateriaRepository.findByNomeContaining(nome, pageable);
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA')")
	public Page<Bateria> pesquisar(@RequestParam(required = false) Pageable pageable) {
		return bateriaRepository.findAll(pageable);
	}

	@PutMapping("/{codigo}/{quantidade}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
	public void atualizarQuantidade(@PathVariable Long codigo, @PathVariable int quantidade) {
		bateriaService.atualizarQuantidade(codigo, quantidade);
	}
}
