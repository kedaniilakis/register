package com.example.register.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	Optional<AppUser> findByEmail(String email);

	@Modifying
	@Transactional
	@Query("update AppUser set enabled=?2 where email=?1")
	int enableUser(String email, boolean enabled);

}
