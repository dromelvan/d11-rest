package org.d11.rest.security;

import org.d11.rest.api.D11RestApiVersion;
import org.springframework.security.core.GrantedAuthority;

public class RoleAuthority implements GrantedAuthority {

    private final Role role;
    private static final long serialVersionUID = D11RestApiVersion.SERIAL_VERSION_UID;

    public RoleAuthority(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return this.role;
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + this.role.name();
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof RoleAuthority) {
            RoleAuthority roleAuthority = (RoleAuthority) object;
            return getRole() != null && roleAuthority.getRole() != null && getRole().equals(roleAuthority.getRole());
        }
        return super.equals(object);
    }

}
