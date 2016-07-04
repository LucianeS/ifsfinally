package br.edu.ifrs.canoas.lds.ifsfinally.controller.selenium;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.withText;

import java.util.concurrent.TimeUnit;

import org.fluentlenium.adapter.FluentTest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import br.edu.ifrs.canoas.lds.ifsfinally.SpringStarterApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringStarterApplication.class, loader = SpringApplicationContextLoader.class)
@WebAppConfiguration
@IntegrationTest
public class DoacaoControllerSeleniumTest extends FluentTest {
	/**
	 * @author Luciane
	 * @Date: 03/07/2016 Description: Authentication test.
	 */
	@Before
	//@Ignore
	public void login() {
		goTo("http://localhost:8080/login");
		fill("#username").with("123@123.123");
		fill("#password").with("123");
		submit("#btLogin");		
	}

	/**
	 * @author Luciane
	 * @Date: 11/06/2016 Description: Create a new student profile test, view
	 *        students profile test and edit student profile test.
	 */
	//@Ignore
	@Test
	public void testCreateViewUpdateEDeleteDoacaoesSemErros() {
		// Cadastro
		goTo("http://localhost:8080/doacao/create");
		assertThat(find("#doacaoTitle").isEmpty());
		fill("#doacaoTitle").with("Teste");
		assertThat(find("#doacaoDescription").isEmpty());
		fill("#doacaoDescription").with("Descrição teste");
		assertThat(find("#contactEmail").isEmpty());
		fill("#contactEmail").with("ana@bol.com");
		assertThat(find("#location").isEmpty());
		fill("#location").with("Canoas/RS");
		find("#doacaoStatus").click();
		submit("#btSubmitForm");

		// View
		await().atMost(5, TimeUnit.SECONDS).until(find("p", withText("Cadastro de Doações")));
		assertThat(find(".alert-success").getText()).isEqualTo("Doação salva!");
		assertThat(find("#doacaoTitle").getValue()).isEqualTo("Teste");
		assertThat(find("#doacaoDescription").getValue()).isEqualTo("Descrição teste");
		assertThat(find("#contactEmail").getValue()).isEqualTo("ana@bol.com");
		assertThat(find("#location").getValue()).isEqualTo("Canoas/RS");
		assertThat(find("#doacaoStatus").getValue()).isEqualTo("true");
		
		// Update
		find("#btEdit").click();
		fill("#doacaoTitle").with("Teste Atualizado");
		fill("#doacaoDescription").with("Descrição teste atualizada");
		fill("#contactEmail").with("ana@gmail.com");
		fill("#location").with("POA/RS");
		find("#doacaoStatus").click();
		submit("#btSubmitForm");

		// View - Dados Editados
		await().atMost(5, TimeUnit.SECONDS).until(find("p", withText("Cadastro de Doações")));
		assertThat(find(".alert-success").getText()).isEqualTo("Doação salva!");
		assertThat(find("#doacaoTitle").getValue()).isEqualTo("Teste Atualizado");
		assertThat(find("#doacaoDescription").getValue()).isEqualTo("Descrição teste atualizada");
		assertThat(find("#contactEmail").getValue()).isEqualTo("ana@gmail.com");
		assertThat(find("#location").getValue()).isEqualTo("POA/RS");
		assertThat(find("#doacaoStatus").getValue()).isEqualTo("true");
		
		//Delete
		find("span").click();
		await().atMost(5, TimeUnit.SECONDS).until(find(".alert alert-success", withText("Doação Teste Atualizado apagada!")));
	}

}