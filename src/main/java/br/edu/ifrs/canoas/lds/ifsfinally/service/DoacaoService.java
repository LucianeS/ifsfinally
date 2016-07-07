package br.edu.ifrs.canoas.lds.ifsfinally.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrs.canoas.lds.ifsfinally.domain.Doacao;
import br.edu.ifrs.canoas.lds.ifsfinally.repository.DoacaoRepository;

@Service
public class DoacaoService {
	
	private DoacaoRepository doacaoRepository;
	
	@Autowired
	public DoacaoService(DoacaoRepository doacaoRepo) {
		this.doacaoRepository = doacaoRepo;
	}
	
	/**
	 * Retorna uma doação específica,
	 * relacionada com o id recebido no parâmetro
	 * @param id
	 * @return
	 */
	public Doacao get(Long id) {
		return doacaoRepository.findOne(id);
	}
	
	/**
	 * Deleta as informações referentes 
	 * ao objeto relacionado ao id passado
	 * no parâmetro
	 * @param id
	 */
	public void delete(Long id) {
		doacaoRepository.delete(id);
	}
	
	/**
	 * Grava as informações contidas no objeto 
	 * recebido por parâmetro
	 */
	public Doacao save(Doacao doacao) {
		return doacaoRepository.save(doacao);
	}
	
	/**
	 * Responsável por retornar a lista de doações com
	 * status disponível, ou seja = true
	 * @return
	 */
	public Object listStatusDisponivel() {
		return doacaoRepository.findByDisponivel(true);
	}
	
	/**
	 * Responsável por retornar a lista
	 * contendo todas as doações cadastradas no banco
	 * de dados
	 * @return
	 */
	public Iterable<Doacao> list() {
		return doacaoRepository.findAll();
	}

}
