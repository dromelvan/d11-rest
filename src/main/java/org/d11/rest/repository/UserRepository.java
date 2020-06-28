package org.d11.rest.repository;

import org.d11.rest.model.jpa.User;

public interface UserRepository extends D11RestRepository<User> {

    public User findByUsername(String username);

}
