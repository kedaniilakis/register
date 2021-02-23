package com.example.register.registration;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RegistrationRequest {

	private final String password;
	private final String email;
	private final String firstName;
	private final String lastName;
	private final LocalDate dateOfBirth;
	private final String userRoles;

}
