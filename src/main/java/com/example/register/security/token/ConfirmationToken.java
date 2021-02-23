package com.example.register.security.token;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.example.register.user.AppUser;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ConfirmationToken {

	private static final String TOKEN_SEQUENCE = "token_sequence";
	@SequenceGenerator(name = TOKEN_SEQUENCE, sequenceName = TOKEN_SEQUENCE, allocationSize = 1, initialValue = 1)
	@Id
	@GeneratedValue(generator = TOKEN_SEQUENCE, strategy = GenerationType.SEQUENCE)
	private Long id;
	private String token;
	private LocalDateTime createdAt;
	private LocalDateTime expiresAt;
	private LocalDateTime confirmedAt;
	@JoinColumn(name = "appUserId")
	@ManyToOne
	private AppUser appUser;

	public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt, AppUser appUser) {
		this.token = token;
		this.createdAt = createdAt;
		this.expiresAt = expiresAt;
		this.appUser = appUser;
	}

}
