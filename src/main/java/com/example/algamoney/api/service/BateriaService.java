package com.example.algamoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamoney.api.model.Bateria;
import com.example.algamoney.api.repository.BateriaRepository;

@Service
public class BateriaService {
	@Autowired
	private BateriaRepository bateriaRepository;

	public Bateria atualizar(Long codigo, Bateria bateria) {
		Bateria bateriaSalva = buscarBateriaPeloCodigo(codigo);

		BeanUtils.copyProperties(bateria, bateriaSalva, "codigo");
		return bateriaRepository.save(bateriaSalva);
	}

	public void atualizarQuantidade(Long codigo, int quantidade) {
		Bateria bateriaSalva = buscarBateriaPeloCodigo(codigo);
		bateriaSalva.setQuantidade(quantidade);
		bateriaRepository.save(bateriaSalva);
	}

	public Bateria buscarBateriaPeloCodigo(Long codigo) {
		Bateria bateriaSalva = bateriaRepository.findOne(codigo);
		if (bateriaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return bateriaSalva;
	}

}
