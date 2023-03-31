package com.example.algamoney.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamoney.api.model.Bateria;

public interface BateriaRepository extends JpaRepository<Bateria, Long> {

	public Page<Bateria> findByNomeContaining(String nome, Pageable pageable);
}
