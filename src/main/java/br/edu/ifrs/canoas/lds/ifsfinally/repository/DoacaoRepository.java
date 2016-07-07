package br.edu.ifrs.canoas.lds.ifsfinally.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.edu.ifrs.canoas.lds.ifsfinally.domain.Doacao;

public interface DoacaoRepository extends CrudRepository<Doacao, Long>{
	/**
	 * Método que retona uma lista de doações com status disponível,
	 * para que sejam mostradas na página inicial do site
	 * @param IsPublic
	 * @return
	 */
	List<Doacao> findByDisponivel(boolean IsPublic);

}
