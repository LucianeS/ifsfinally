package br.edu.ifrs.canoas.lds.ifsfinally.service;

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
	
	public Doacao get(Long id) {
		return doacaoRepository.findOne(id);
	}
	
	public void delete(Long id) {
		doacaoRepository.delete(id);
	}
	
	public Doacao save(Doacao doacao) {
		return doacaoRepository.save(doacao);
	}	

}
