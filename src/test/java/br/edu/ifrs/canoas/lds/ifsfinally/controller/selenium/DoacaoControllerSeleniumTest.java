package br.edu.ifrs.canoas.lds.ifsfinally.controller.selenium;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.withId;
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
	 * @Date: 03/07/2016 
	 * Description: Testa o cadastramento de uma doação,
	 * bem como a edição das infromações do cadastro 
	 * e o sua deleção, sem erros.
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
	
	/**
	 * @author Luciane
	 * @Date: 04/07/2016 
	 * Description: Testa o cadastramento de uma doação,
	 * inserindo dados incorretos, para testar as validações
	 * do formulário
	 */
	//@Ignore
	@Test
	public void testCreateDoacaoesComErros() {
		// Cadastro
		goTo("http://localhost:8080/doacao/create");
		assertThat(find("#doacaoTitle").isEmpty());
		fill("#doacaoTitle").with("");
		assertThat(find("#doacaoDescription").isEmpty());
		fill("#doacaoDescription").with("");
		assertThat(find("#contactEmail").isEmpty());
		fill("#contactEmail").with("");
		assertThat(find("#location").isEmpty());
		fill("#location").with("");
		find("#doacaoStatus").click();
		submit("#btSubmitForm");
		
		assertThat(find(".help-block").getText()).isEqualTo("may not be empty");
		
		goTo("http://localhost:8080/doacao/create");
		assertThat(find("#doacaoTitle").isEmpty());
		fill("#doacaoTitle").with("Titulo doação");
		assertThat(find("#doacaoDescription").isEmpty());
		fill("#doacaoDescription").with("");
		assertThat(find("#contactEmail").isEmpty());
		fill("#contactEmail").with("ana@bol.com");
		assertThat(find("#location").isEmpty());
		fill("#location").with("Canoas/RS");
		find("#doacaoStatus").click();
		submit("#btSubmitForm");
		
		assertThat(find(".help-block").getText()).isEqualTo("size must be between 1 and 500");
		
		goTo("http://localhost:8080/doacao/create");
		assertThat(find("#doacaoTitle").isEmpty());
		fill("#doacaoTitle").with("Titulo doação");
		assertThat(find("#doacaoDescription").isEmpty());
		fill("#doacaoDescription").with("Descrição da doação");
		assertThat(find("#contactEmail").isEmpty());
		fill("#contactEmail").with("ana.net");
		assertThat(find("#location").isEmpty());
		fill("#location").with("POA/RS");
		find("#doacaoStatus").click();
		submit("#btSubmitForm");
		
		assertThat(find(".help-block").getText()).isEqualTo("not a well-formed email address");
	}
	
	/**
	 * @author Luciane
	 * @Date: 04/07/2016 
	 * Description: Testa a permissão de editar e deletar doações
	 * Pois somente o usuário logado com perfil de responsável pela doação
	 * pode editá-la ou apagá-la
	 */
	//@Ignore
	@Test
	public void testeListUpdateEDeleteDoacoesDeTerceiros() {
		//Edit
		goTo("http://localhost:8080/doacao/list");
		find("td", 0).click();
		await().atMost(5, TimeUnit.SECONDS).until(find("p", withText("Cadastro de Doações")));
		find("#btEdit").click();
		await().atMost(5, TimeUnit.SECONDS).until(find(".alert alert-success", withText("Você não tem permissão para editar esta doação.")));
		
		//Delete
		find("td", 3).click();
		await().atMost(5, TimeUnit.SECONDS).until(find("p", withText("Cadastro de Doações")));
		find("#btDelete").click();
		await().atMost(5, TimeUnit.SECONDS).until(find(".alert alert-success", withText("Você não tem permissão para deletar esta doação.")));
		
	}
	
	/**
	 * @author Luciane
	 * @Date: 04/07/2016 
	 * Description: Testa as funções update e delete com 
	 * o usuário logado com perfil de responsável pela doação
	 */
	//@Ignore
	@Test
	public void testeCreateViewUpdateEDeleteDoacoesAutenticadoComPerfilDeReposnável() {
		//Create
		goTo("http://localhost:8080/doacao/create");
		assertThat(find("#doacaoTitle").isEmpty());
		fill("#doacaoTitle").with("Teste");
		assertThat(find("#doacaoDescription").isEmpty());
		fill("#doacaoDescription").with("Teste");
		assertThat(find("#contactEmail").isEmpty());
		fill("#contactEmail").with("teste@gmail.com");
		assertThat(find("#location").isEmpty());
		fill("#location").with("POA/RS");
		find("#doacaoStatus").click();
		submit("#btSubmitForm");
		
		// View
		await().atMost(5, TimeUnit.SECONDS).until(find("p", withText("Cadastro de Doações")));
		assertThat(find(".alert-success").getText()).isEqualTo("Doação salva!");
		assertThat(find("#doacaoTitle").getValue()).isEqualTo("Teste");
		assertThat(find("#doacaoDescription").getValue()).isEqualTo("Teste");
		assertThat(find("#contactEmail").getValue()).isEqualTo("teste@gmail.com");
		assertThat(find("#location").getValue()).isEqualTo("POA/RS");
		assertThat(find("#doacaoStatus").getValue()).isEqualTo("true");
		
		
		//Edit
		find("#btEdit").click();
		fill("#doacaoTitle").with("Teste Atualizado");
		fill("#doacaoDescription").with("Descrição teste atualizada");
		fill("#contactEmail").with("emailAtualizado@gmail.com");
		fill("#location").with("Canoas/RS");
		find("#doacaoStatus").click();
		submit("#btSubmitForm");
		await().atMost(5, TimeUnit.SECONDS).until(find("p", withText("Cadastro de Doações")));
		assertThat(find(".alert-success").getText()).isEqualTo("Doação salva!");
		
		// View - Dados atualizados
		await().atMost(5, TimeUnit.SECONDS).until(find("p", withText("Cadastro de Doações")));
		assertThat(find(".alert-success").getText()).isEqualTo("Doação salva!");
		assertThat(find("#doacaoTitle").getValue()).isEqualTo("Teste Atualizado");
		assertThat(find("#doacaoDescription").getValue()).isEqualTo("Descrição teste atualizada");
		assertThat(find("#contactEmail").getValue()).isEqualTo("emailAtualizado@gmail.com");
		assertThat(find("#location").getValue()).isEqualTo("Canoas/RS");
		assertThat(find("#doacaoStatus").getValue()).isEqualTo("true");
		
		//Delete
		find("#btDelete").click();
		await().atMost(5, TimeUnit.SECONDS).until(find(".alert alert-success", withText("Doação teste atualizado apagada!")));
	}
	
	/**
	 * @author Luciane
	 * @Date: 04/07/2016 
	 * Description: Testa o logout, o view
	 * e a navegação de visitante no site (FrontEnd).
	 */
	//@Ignore
	@Test
	public void testeLogoutViewENavegaçãoComUsuárioVisitante() {
		//Logout
		goTo("http://localhost:8080");
		find(".navbar-brand", withText("Logout")).click();
		await().atMost(5, TimeUnit.SECONDS).until(find(".alert-success", withText("You have been logged out.")));
		//assertThat(find(".alert-success").getText()).isEqualTo("You have been logged out.");
		
		findFirst("A", withText("Home")).click();
		await().atMost(5, TimeUnit.SECONDS).until(find("h1", withText("Brechó Virtual")));
		
		findFirst("h2", withText("Roupeiro em ótimo estado de conservação")).click();
		
		//View
		await().atMost(5, TimeUnit.SECONDS).until(find("p", withText("Cadastro de Doações")));
		assertThat(find("#doacaoTitle").getValue()).isEqualTo("Roupeiro em ótimo estado de conservação");
		assertThat(find("#doacaoDescription").getValue()).isEqualTo("Roupeiro com 3 anos de uso em MDF, que se interessar deve marcar uma data para buscar, pois não posso arcar com frete");
		assertThat(find("#contactEmail").getValue()).isEqualTo("ana@bol.com");
		assertThat(find("#location").getValue()).isEqualTo("Canoas/RS");
		assertThat(find("#doacaoStatus").getValue()).isEqualTo("true");
		find("#voltCancel").click();
		await().atMost(5, TimeUnit.SECONDS).until(find("h1", withText("Brechó Virtual")));
		
		//Tentativa de cadastro (redireciona para login)
		find("li", 0).click();
		await().atMost(5, TimeUnit.SECONDS).until(find("p", withText("Informe seus dados")));
		
		//Voltando para a Home
		find("li", 0).click();
		await().atMost(5, TimeUnit.SECONDS).until(find("h1", withText("Brechó Virtual")));
		
		//Tentativa de visualizar a página List (redireciona para login)
		find("li", 1).click();
		await().atMost(5, TimeUnit.SECONDS).until(find("p", withText("Informe seus dados")));
	}

}