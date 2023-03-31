package com.example.algamoney.api.service;

import com.example.algamoney.api.model.Produto;
import com.example.algamoney.api.repository.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto atualizar(Long codigo, Produto bateria) {
        Produto produtoSalvo = buscarProdutoPeloCodigo(codigo);

        BeanUtils.copyProperties(bateria, produtoSalvo, "codigo");
        return produtoRepository.save(produtoSalvo);
    }

    public void atualizarQuantidade(Long codigo, int quantidade) {
        Produto produtoSalvo = buscarProdutoPeloCodigo(codigo);
        produtoSalvo.setQuantidade(quantidade);
        produtoRepository.save(produtoSalvo);
    }

    public Produto buscarProdutoPeloCodigo(Long codigo) {
        Produto produtoSalvo = produtoRepository.findOne(codigo);
        if (produtoSalvo == null) {
            throw new EmptyResultDataAccessException(1);
        }
        return produtoSalvo;
    }

}
