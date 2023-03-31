package com.example.algamoney.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamoney.api.model.Loja;

public interface LojaRepository extends JpaRepository<Loja, Long> {

	public Page<Loja> findByNomeContaining(String nome, Pageable pageable);
	
}
