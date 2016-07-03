package br.edu.ifrs.canoas.lds.ifsfinally.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.edu.ifrs.canoas.lds.ifsfinally.SpringStarterApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SpringStarterApplication.class)
public class DoacaoRepositoryTest {
	
	@Autowired
	DoacaoRepository doacaoRepository;
	
	/**
	 * @author Luciane
	 * @Date: 02/07/2016
	 * Descrição: Testa o método que encontra as doações com status 
	 *  marcado como disponível
	 */
	@Test
	public void testFindByDoacoesWithStatusDisponivel() {
		assertThat(doacaoRepository.findByDisponivel(true).size(), is(3));
	}

}
