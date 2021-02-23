package com.example.register.user;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.register.security.UserRoles;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class AppUser implements UserDetails {

	private static final String APP_USER_SEQUENCE = "app_user_sequence";
	@Id
	@SequenceGenerator(sequenceName = APP_USER_SEQUENCE, allocationSize = 1, initialValue = 1, name = APP_USER_SEQUENCE)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = APP_USER_SEQUENCE)
	private Long id;
	@Column(name = "user_name", nullable = false)
	private String userName;
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	@Column(name = "first_name", nullable = false)
	private String firstName;
	@Column(name = "last_name", nullable = false)
	private String lastName;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "enabled", nullable = false)
	private Boolean enabled = false;
	@Column(name = "disabled", nullable = false)
	private Boolean locked = false;
	@Column(name = "birth_date", nullable = false)
	private LocalDate dateOfBirth;
	@Enumerated(EnumType.STRING)
	@Column(name = "user_roles", nullable = false)
	private UserRoles userRole;
	
	

	public AppUser(String email, String firstName, String lastName, String password, LocalDate dateOfBirth,
			UserRoles userRole) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.userName = email;
		this.userRole = userRole;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authorityUser = new SimpleGrantedAuthority(userRole.name());
		List<SimpleGrantedAuthority> authoritiesList = Arrays.asList(authorityUser);
		return authoritiesList;
	}
	
	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

}
