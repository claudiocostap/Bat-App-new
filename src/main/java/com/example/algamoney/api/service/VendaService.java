package com.example.algamoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamoney.api.model.Venda;
import com.example.algamoney.api.repository.VendaRepository;

@Service
public class VendaService {
	@Autowired
	private VendaRepository vendaRepository;

	public Venda atualizar(Long codigo, Venda venda) {
		Venda vendaSalva = buscarVendaPeloCodigo(codigo);

		BeanUtils.copyProperties(venda, vendaSalva, "codigo");
		return vendaRepository.save(vendaSalva);
	}

	public Venda buscarVendaPeloCodigo(Long codigo) {
		Venda vendaSalva = vendaRepository.findOne(codigo);
		if (vendaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return vendaSalva;
	}

}
