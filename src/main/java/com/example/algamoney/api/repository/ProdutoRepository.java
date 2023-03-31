package com.example.algamoney.api.repository;


import com.example.algamoney.api.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    public Page<Produto> findByNomeContaining(String nome, Pageable pageable);
}
