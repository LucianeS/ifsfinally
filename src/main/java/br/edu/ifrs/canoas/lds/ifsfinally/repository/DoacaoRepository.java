package br.edu.ifrs.canoas.lds.ifsfinally.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.edu.ifrs.canoas.lds.ifsfinally.domain.Doacao;

public interface DoacaoRepository extends CrudRepository<Doacao, Long>{
	
	List<Doacao> findByDisponivel(boolean IsPublic);

}
