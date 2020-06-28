package org.d11.rest.security.authentication;

import java.util.Collection;

import org.d11.rest.D11RestVersion;
import org.d11.rest.api.model.UserDTO;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class D11RestAuthentication extends AbstractAuthenticationToken {

    private UserDTO principal;
    private String credentials;
    private static final long serialVersionUID = D11RestVersion.SERIAL_VERSION_UID;

    public D11RestAuthentication(UserDTO userDTO, Collection<? extends GrantedAuthority> authorities, String token) {
        super(authorities);
        this.principal = userDTO;
        this.credentials = token;
        setAuthenticated(true);
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

}
