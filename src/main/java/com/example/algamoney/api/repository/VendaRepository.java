package com.example.algamoney.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamoney.api.model.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {

	public Page<Venda> findByCodigoContaining(String codigo, Pageable pageable);
}
