/**
 * @author:
 * @date: 
 * @description: 
 */
package br.edu.ifrs.canoas.lds.ifsfinally.controller;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

public class BaseControllerTest {

	final String BASE_URL = "http://localhost:8080/";
	final String PRE_URL = "/WEB-INF/jsp/view/";
	final String POS_URL = ".jsp";
	protected MockMvc mockMvc;
	protected InternalResourceViewResolver viewResolver;
	
	
	/**
	 * Gets the mock mvc.
	 *
	 * @param controller
	 *            the controller
	 * @return the mock mvc
	 */
	public MockMvc getMockMvc(Object controller){
		viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix(PRE_URL);
        viewResolver.setSuffix(POS_URL);
		return MockMvcBuilders.standaloneSetup(controller).setViewResolvers(viewResolver).build();
	}
			
}

