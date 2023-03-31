package com.example.algamoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamoney.api.model.Bateria;
import com.example.algamoney.api.model.Investimento;
import com.example.algamoney.api.repository.BateriaRepository;
import com.example.algamoney.api.repository.InvestimentoRepository;

@Service
public class InvestimentoService {
	@Autowired
	private InvestimentoRepository investimentoRepository;

	public Investimento atualizar(Long codigo, Investimento investimento) {
		Investimento investimentoSalvo = buscarinvestimentoPeloCodigo(codigo);

		BeanUtils.copyProperties(investimento, investimentoSalvo, "codigo");
		return investimentoRepository.save(investimentoSalvo);
	}

	public Investimento buscarinvestimentoPeloCodigo(Long codigo) {
		Investimento investimentoSalvo = investimentoRepository.findOne(codigo);
		if (investimentoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return investimentoSalvo;
	}

}
