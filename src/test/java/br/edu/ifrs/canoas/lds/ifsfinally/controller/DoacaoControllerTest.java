package br.edu.ifrs.canoas.lds.ifsfinally.controller;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import br.edu.ifrs.canoas.lds.ifsfinally.SpringStarterApplication;
import br.edu.ifrs.canoas.lds.ifsfinally.domain.Doacao;
import br.edu.ifrs.canoas.lds.ifsfinally.service.DoacaoService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SpringStarterApplication.class)
public class DoacaoControllerTest extends BaseControllerTest{
	
	@Autowired 
	DoacaoController doacaoController;
	
	@Autowired 
	DoacaoService doacaoService;
	
	@Before
	public void setup() {
		mockMvc = getMockMvc(doacaoController);
	}
	
	/**
	 * @author Luciane
	 * @Date: 02/07/2016
	 * Descrição: Testa o método list que mostra a
	 * lista de doações cadastradas no sistema,
	 * checando o número de doações listadas e verificando
	 * o título de uma delas.
	 */
	@Test
	public void testToListDoacoesAndCheckAtts() throws Exception{
		this.mockMvc.perform(get("/doacao/list"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("doacoes"))
		.andExpect(model().attribute("doacoes", hasSize(4)))
		.andExpect(model().attribute("doacoes", hasItem(
                allOf(
                        hasProperty("id", is(1L)),
                        hasProperty("title", is("Roupeiro em ótimo estado de conservação"))
                )
        )))
		.andExpect(forwardedUrl(PRE_URL+"/doacao/list"+POS_URL))
		;
		
	}
	
	/**
	 * @author Luciane
	 * @Date: 02/07/2016
	 * Descrição: Testa o método que cria novas doações
	 */
	@Test
	@WithUserDetails("123@123.123")
	public void testToCreateANewDoacaoAndCheckAtts() throws Exception {
		this.mockMvc.perform(post("/doacao/create"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("doacao"))
		.andExpect(model().attribute("doacao", hasProperty("title", isEmptyOrNullString())))
		.andExpect(model().attribute("readonly", is(false)))
		.andExpect(forwardedUrl(PRE_URL+"/doacao/form"+POS_URL))
		;
		
	}

	/**
	 * @author Luciane
	 * @Date: 02/07/2016
	 * Descrição: Testa o método save, verificando
	 * se ele está criando uma nova doação
	 */
	@Test
	@WithUserDetails("123@123.123")
	public void testToSaveAFormWithBadData() throws Exception {
		
		Long size = doacaoService.list().spliterator().getExactSizeIfKnown();
		
		mockMvc.perform(post("/doacao/save")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("paramTeste", "paramTeste")
                .sessionAttr("doacao", new Doacao())
        )
	    .andDo(MockMvcResultHandlers.print())
	    .andExpect(status().isOk())
	    .andExpect(view().name("/doacao/form"))
	    .andExpect(model().attribute("readonly", is(false)))
	    ;
		
		assertThat(doacaoService.list().spliterator().getExactSizeIfKnown(), is(size));

	}
	
	/**
	 * @author Luciane
	 * @Date: 02/07/2016
	 * Descrição: Testa o método view da uma doação
	 * cadastrada com id=2 e verifica o título da mesma.
	 */
	@Test
	public void testToViewDoacao2AndCheckAtts() throws Exception {
		this.mockMvc.perform(post("/doacao/view/2"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("doacao"))
		.andExpect(model().attribute("doacao", hasProperty("title", is("Dou cama de solteiro novinha!"))))
		.andExpect(model().attribute("readonly",is(true)))
		.andExpect(forwardedUrl(PRE_URL+"/doacao/form"+POS_URL))
		;		
	}
	
	/**
	 * @author Luciane
	 * @Date: 02/07/2016
	 * Descrição: Testa o método view para o caso 
	 * de o usuário tentar verificar uma doação que 
	 * existe no cadastro, exemplo id=100
	 */
	@Test
	public void testToViewDoacao100ThatDoesNotExists() throws Exception {
		assertThat(doacaoService.get(100L), is(nullValue()));
		
		this.mockMvc.perform(post("/doacao/view/100"))
		.andExpect(view().name("redirect:/"))
		.andExpect(status().is3xxRedirection())
		.andExpect(flash().attributeExists("message"))
		;		
	}
	
	/**
	 * @author Luciane
	 * @Date: 02/07/2016
	 * Descrição: Testa o método delete, tentando apagar 
	 * a doação com id=3 
	 */
	@Test
	@WithUserDetails("lucas@123.123")
	public void testToCheckDoacao3DeleteItAndCheckAgain() throws Exception {
		
		assertThat(doacaoService.get(3L), is(notNullValue()));
		assertThat(doacaoService.get(3L).getTitle(), is("Pilcha completa em ótimo estado"));
		
		this.mockMvc.perform(post("/doacao/delete/3"))
			.andExpect(flash().attributeExists("message"))
			;
		
		assertThat(doacaoService.get(3L), is(nullValue()));

	}
	
	/**
	 * @author Luciane
	 * @Date: 02/07/2016
	 * Descrição: Testa o método delete, tentando apagar 
	 * a doação com id=1000, que não existe no cadastro
	 */
	@Test
	@WithUserDetails("123@123.123")
	public void testToDeleteDoacao1000ThatDoesNotExists() throws Exception {
		
		assertThat(doacaoService.get(1000L), is(nullValue()));
		
		this.mockMvc.perform(post("/doacao/delete/1000"))
		.andExpect(view().name("redirect:/"))
		.andExpect(status().is3xxRedirection())
		.andExpect(flash().attributeExists("message"))
		;


	}
	
	/**
	 * @author Luciane
	 * @Date: 02/07/2016
	 * DDescrição: Testa o método delete, com o usuário autenticado
	 * com perfil diferente do autor da doação, ou seja, sem permissão de deletar
	 */
	@Test
	@WithUserDetails("123@123.123")
	public void testToDeleteDoacao4WithUserLoginIncorreto() throws Exception {
		
		assertThat(doacaoService.get(4L), is(notNullValue()));
		
		this.mockMvc.perform(post("/doacao/delete/4"))
		.andExpect(view().name("redirect:/doacao/list"))
		.andExpect(status().is3xxRedirection())
		.andExpect(flash().attributeExists("message"))
		;
	}
	
	/**
	 * @author Luciane
	 * @Date: 02/07/2016
	 * Descrição: Testa o método update, editando a propriedade 
	 * title da doação com id=4, com o usuário devidamente autenticado
	 * com perfil de autor da doação, ou seja, com permissão de editar
	 */
	@Test
	@WithUserDetails("marco@123.123")
	public void testToUpdateDoacao4AndCheckAtts() throws Exception {
		this.mockMvc.perform(post("/doacao/edit/4"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("doacao"))
		.andExpect(model().attribute("doacao", hasProperty("title", is("Mesa com 4 cadeiras"))))
		.andExpect(model().attribute("readonly",is(false)))
		.andExpect(forwardedUrl(PRE_URL+"/doacao/form"+POS_URL))
		;
	}
	
	/**
	 * @author Luciane
	 * @Date: 02/07/2016
	 * Descrição: Testa o método update, com o usuário autenticado
	 * com perfil diferente do autor da doação, ou seja, sem permissão de editar
	 */
	@Test
	@WithUserDetails("123@123.123")
	public void testToUpdateDoacao4WithUserLoginIncorreto() throws Exception {
		
		assertThat(doacaoService.get(4L), is(notNullValue()));
		
		this.mockMvc.perform(post("/doacao/edit/4"))
		.andExpect(view().name("redirect:/doacao/list"))
		.andExpect(status().is3xxRedirection())
		.andExpect(flash().attributeExists("message"))
		;
	}

}
