package com.example.register.user;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AppUserService implements UserDetailsService {

	private AppUserRepository appUserRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return appUserRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("Username: %s not found", email)));
	}

	public void saveUser(AppUser user) {
		appUserRepository.save(user);
	}

	public List<AppUser> getAllUsers() {
		return appUserRepository.findAll();
	}

	public int enableUser(String email, boolean enabled) {
		return appUserRepository.enableUser(email, enabled);
	}
	
	

}
