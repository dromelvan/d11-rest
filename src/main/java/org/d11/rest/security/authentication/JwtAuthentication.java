package org.d11.rest.security.authentication;

import org.d11.rest.api.D11RestApiVersion;
import org.springframework.security.authentication.AbstractAuthenticationToken;

public class JwtAuthentication extends AbstractAuthenticationToken {

    private Object credentials;
    private static final long serialVersionUID = D11RestApiVersion.SERIAL_VERSION_UID;

    public JwtAuthentication(String token) {
        super(null);
        this.credentials = token;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

}
