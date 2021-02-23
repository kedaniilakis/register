package com.example.register.registration;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.register.user.AppUser;
import com.example.register.user.AppUserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1")
@AllArgsConstructor
public class RegistrationController {

	private final RegistrationService registrationService;
	private final AppUserService appUserService;

	@PostMapping("admin/signup")
	public String saveUser(@RequestBody RegistrationRequest request) {
		return registrationService.signUpUser(request);
	}

	@GetMapping("admin/confirmToken/{token}")
	public String confirmToken(@PathVariable String token) {
		registrationService.confirmToken(token);
		return "Confirmed";
	}

	@GetMapping("users")
	public List<AppUser> getUsers() {
		List<AppUser> allUsers = appUserService.getAllUsers();
		return allUsers;
	}
}
