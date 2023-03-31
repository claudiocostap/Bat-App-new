package com.example.algamoney.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoney.api.model.Investimento;
import com.example.algamoney.api.repository.InvestimentoRepository;
import com.example.algamoney.api.service.InvestimentoService;

@RestController
@RequestMapping("/investimentos")
public class InvestimentoResource {

	@Autowired
	private InvestimentoRepository investimentoRepository;

	@Autowired
	private InvestimentoService investimentoService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_INVESTIMENTO') and #oauth2.hasScope('read')")
	public ResponseEntity<Investimento> buscarPeloCodigo(@PathVariable Long codigo) {
		Investimento investimento = investimentoRepository.findOne(codigo);
		return investimento != null ? ResponseEntity.ok(investimento) : ResponseEntity.notFound().build();
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_INVESTIMENTO')")
	public Page<Investimento> pesquisar(@RequestParam(required = false, defaultValue = "%") String nome,
			Pageable pageable) {
		return investimentoRepository.findByNomeContaining(nome, pageable);
	}

}
