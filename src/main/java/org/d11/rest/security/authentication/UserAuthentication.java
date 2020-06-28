package org.d11.rest.security.authentication;

import org.d11.rest.api.D11RestApiVersion;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class UserAuthentication extends UsernamePasswordAuthenticationToken {

    private static final long serialVersionUID = D11RestApiVersion.SERIAL_VERSION_UID;

    public UserAuthentication(String username, String password) {
        super(username, password);
    }

}
