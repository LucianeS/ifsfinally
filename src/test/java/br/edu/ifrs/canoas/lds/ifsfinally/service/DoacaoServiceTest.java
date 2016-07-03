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

	@Test
	public void testGetDoacao2AndCheckContactEmail() {
		assertThat(doacaoService.get(2L), hasProperty("contactEmail", is("junior@gmail.com")));
	}

	@Test
	@WithUserDetails("ana@123.123")
	public void testFindDoacao1DeleteItAndCheckAgain() {
		assertThat(doacaoService.get(1L), hasProperty("title", is("Roupeiro em ótimo estado de conservação")));
		doacaoService.delete(1L);
		assertThat(doacaoService.get(1L), is(nullValue()));
	}

	@Test
	public void testCreateAndSaveAnDoacao() {
		Doacao doacao = new Doacao();
		doacao.setTitle("Minha doação");
		assertThat(doacaoService.save(doacao), hasProperty("id", is(not(empty()))));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testListStatusDisponivel() {
		assertTrue(((Iterable<Doacao>) doacaoService.listStatusDisponivel()).spliterator().estimateSize() > 0);
	}

	@Test
	public void testList() {
		assertTrue(doacaoService.list().spliterator().estimateSize() > 0);
	}

}
