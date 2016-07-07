package br.edu.ifrs.canoas.lds.ifsfinally.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.edu.ifrs.canoas.lds.ifsfinally.SpringStarterApplication;
import br.edu.ifrs.canoas.lds.ifsfinally.domain.Doacao;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SpringStarterApplication.class)
public class DoacaoServiceTest {
	
	@Autowired
	DoacaoService doacaoService;
	
	/**
	 * @author Luciane
	 * @Date: 02/07/2016
	 * Descrição: Testa o método get, com id=2
	 * e checa se a propriedade contactEmail
	 * é igual a passada por parâmetro
	 */
	@Test
	public void testGetDoacao2AndCheckContactEmail() {
		assertThat(doacaoService.get(2L), hasProperty("contactEmail", is("junior@gmail.com")));
	}
	
	/**
	 * @author Luciane
	 * @Date: 02/07/2016
	 * Descrição: Testa o método findOne, para encontrar 
	 * a doação com id=1 e checa se o title é igual ao passado por
	 * parâmetro, em seguida chama o método delete e verifica
	 * se o id está nulo, ou seja, a doação foi apagada.
	 */
	@Test
	@WithUserDetails("ana@123.123")
	public void testFindDoacao1DeleteItAndCheckAgain() {
		assertThat(doacaoService.get(1L), hasProperty("title", is("Roupeiro em ótimo estado de conservação")));
		doacaoService.delete(1L);
		assertThat(doacaoService.get(1L), is(nullValue()));
	}
	
	/**
	 * @author Luciane
	 * @Date: 02/07/2016
	 * Descrição: Testa a criação de uma nova doação, 
	 * salvando a mesma no banco e verifica se a 
	 * propriedade id não está vazia, ou seja, a doação 
	 * foi criada com sucesso
	 */
	@Test
	public void testCreateAndSaveAnDoacao() {
		Doacao doacao = new Doacao();
		doacao.setTitle("Minha doação");
		assertThat(doacaoService.save(doacao), hasProperty("id", is(not(empty()))));
	}
	
	/**
	 * @author Luciane
	 * @Date: 02/07/2016
	 * Descrição: Testa se o tamanho da lista de 
	 * doações com status disponível, é maior do que zero.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testListStatusDisponivel() {
		assertTrue(((Iterable<Doacao>) doacaoService.listStatusDisponivel()).spliterator().estimateSize() > 0);
	}
	
	/**
	 * @author Luciane
	 * @Date: 02/07/2016
	 * Descrição: Testa se o tamanho da lista contendo 
	 * todas as doações com status disponível, é maior 
	 * do que zero.
	 */
	@Test
	public void testList() {
		assertTrue(doacaoService.list().spliterator().estimateSize() > 0);
	}

}
