/*
 * 
 */
package br.edu.ifrs.canoas.lds.starter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.edu.ifrs.canoas.lds.starter.domain.User;
import br.edu.ifrs.canoas.lds.starter.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	private UserRepository userRepository;

	/**
	 * Instantiates a new user service impl.
	 *
	 * @param userRepository
	 *            the user repository
	 */
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new UserDetailsImpl(user);
	}

}
