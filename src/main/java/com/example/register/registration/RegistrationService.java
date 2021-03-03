package com.example.register.registration;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.register.email.EmailSender;
import com.example.register.security.UserRoles;
import com.example.register.security.token.ConfirmationToken;
import com.example.register.security.token.ConfirmationTokenService;
import com.example.register.user.AppUser;
import com.example.register.user.AppUserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistrationService {
	private final AppUserService userService;
	private final ConfirmationTokenService confirmationTokenService;
	private final EmailSender sender;
	private final BCryptPasswordEncoder encoder;

	public String signUpUser(RegistrationRequest request) {
		AppUser user = new AppUser(request.getEmail(), request.getFirstName(), request.getLastName(),
				encoder.encode(request.getPassword()), request.getDateOfBirth(),
				UserRoles.valueOf(request.getUserRoles().toUpperCase()));

		userService.saveUser(user);

		String token = UUID.randomUUID().toString();

		ConfirmationToken confToken = new ConfirmationToken(token, LocalDateTime.now(),
				LocalDateTime.now().plusMinutes(10), user);
		confirmationTokenService.saveToken(confToken);
		sender.send(user.getEmail(),
				String.format("<a href=\"http://${HOST_NAME}/api/v1/admin/confirmToken/%s\">Confirm Email</a>", token));
		return token;

	}

	public void confirmToken(String token) {
		ConfirmationToken confirmationToken = confirmationTokenService.findToken(token);

		if (confirmationToken.getConfirmedAt() != null) {
			throw new IllegalStateException("email already confirmed");
		}
		confirmationToken.setConfirmedAt(LocalDateTime.now());
		if (confirmationToken.getConfirmedAt().isAfter(confirmationToken.getExpiresAt())) {
			throw new IllegalStateException("Email expired");
		}
		confirmationTokenService.setConfirmedAtNow(token);

		userService.enableUser(confirmationToken.getAppUser().getUsername(), true);

	}
}
