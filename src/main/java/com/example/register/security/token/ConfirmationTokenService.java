package com.example.register.security.token;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

	private final TokenRepository tokenRepository;

	public String saveToken(ConfirmationToken token) {
		tokenRepository.save(token);
		return token.getToken();
	}

	public ConfirmationToken findToken(String uuid) {
		ConfirmationToken token = tokenRepository.findByToken(uuid);
		return token;
	}

	public int setConfirmedAtNow(String token) {
		return tokenRepository.setConfirmedAtNow(token, LocalDateTime.now());
	}
}
