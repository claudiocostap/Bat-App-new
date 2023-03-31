package com.example.algamoney.api.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import com.example.algamoney.api.model.Loja;
import com.example.algamoney.api.repository.LojaRepository;
import com.example.algamoney.api.service.LojaService;

@RestController
@RequestMapping("/lojas")
public class LojaResource {

	@Autowired
	private LojaRepository lojaRepository;

	@Autowired
	private LojaService lojaService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
	public ResponseEntity<Loja> criar(@Valid @RequestBody Loja loja, HttpServletResponse response) {
		Loja lojaSalva = lojaRepository.save(loja);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, lojaSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(lojaSalva);
	}

	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and #oauth2.hasScope('read')")
	public ResponseEntity<Loja> buscarPeloCodigo(@PathVariable Long codigo) {
		Loja loja = lojaRepository.findOne(codigo);
		return loja != null ? ResponseEntity.ok(loja) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_PESSOA') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		lojaRepository.delete(codigo);
	}

	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
	public ResponseEntity<Loja> atualizar(@PathVariable Long codigo, @Valid @RequestBody Loja loja) {
		Loja lojaSalva = lojaService.atualizar(codigo, loja);
		return ResponseEntity.ok(lojaSalva);
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA')")
	public Page<Loja> pesquisar(@RequestParam(required = false, defaultValue = "%") String nome, Pageable pageable) {
		return lojaRepository.findByNomeContaining(nome, pageable);
	}

}
