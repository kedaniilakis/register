package com.example.register.security;

import java.util.Collections;
import java.util.Set;

public enum UserRoles {

	ADMIN(Collections.emptySet()), USER(Set.of(UserPermissions.USER_READ, UserPermissions.USER_WRITE));

	private final Set<UserPermissions> permissions;

	private UserRoles(Set<UserPermissions> permissions) {
		this.permissions = permissions;

	}

	public Set<UserPermissions> getPermissions() {
		return permissions;
	}
}
