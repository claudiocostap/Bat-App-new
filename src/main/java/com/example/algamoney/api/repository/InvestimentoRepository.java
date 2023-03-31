package com.example.algamoney.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamoney.api.model.Investimento;

public interface InvestimentoRepository extends JpaRepository<Investimento, Long> {

	public Page<Investimento> findByNomeContaining(String nome, Pageable pageable);
}
