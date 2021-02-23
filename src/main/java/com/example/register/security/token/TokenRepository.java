package com.example.register.security.token;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TokenRepository extends JpaRepository<ConfirmationToken, Long> {
	ConfirmationToken findByToken(String token);

	@Modifying
	@Transactional
	@Query("update ConfirmationToken set confirmedAt=?2 where token=?1")
	int setConfirmedAtNow(String token, LocalDateTime now);
}
