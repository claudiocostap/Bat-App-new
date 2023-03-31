package com.example.algamoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamoney.api.model.Loja;
import com.example.algamoney.api.repository.LojaRepository;

@Service
public class LojaService {
	@Autowired
	private LojaRepository lojaRepository;

	public Loja atualizar(Long codigo, Loja loja) {
		Loja lojaSalva = buscarLojaPeloCodigo(codigo);

		BeanUtils.copyProperties(loja, lojaSalva, "codigo");
		return lojaRepository.save(lojaSalva);
	}

	public Loja buscarLojaPeloCodigo(Long codigo) {
		Loja lojaSalva = lojaRepository.findOne(codigo);
		if (lojaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return lojaSalva;
	}

}
