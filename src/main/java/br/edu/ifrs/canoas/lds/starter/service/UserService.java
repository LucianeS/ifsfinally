/**
 * @author:
 * @date: 
 * @description: 
 */
package br.edu.ifrs.canoas.lds.starter.service;

import br.edu.ifrs.canoas.lds.starter.domain.User;

public interface UserService {

	/**
	 * Find by email.
	 *
	 * @param email
	 *            the email
	 * @return the user
	 */	
	public User findByEmail(String email);
	
	

}
