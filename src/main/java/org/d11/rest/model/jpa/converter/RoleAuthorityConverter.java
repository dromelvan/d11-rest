package org.d11.rest.model.jpa.converter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.d11.rest.security.Role;
import org.d11.rest.security.RoleAuthority;

@Converter
public class RoleAuthorityConverter implements AttributeConverter<List<RoleAuthority>, Boolean> {

    @Override
    public Boolean convertToDatabaseColumn(List<RoleAuthority> roleAuthorities) {
        for (RoleAuthority roleAuthority : roleAuthorities) {
            if (roleAuthority.getRole() == Role.ADMINISTRATOR) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<RoleAuthority> convertToEntityAttribute(Boolean administrator) {
        List<RoleAuthority> roleAuthorities = new ArrayList<>();
        roleAuthorities.add(new RoleAuthority(Role.USER));
        if (administrator) {
            roleAuthorities.add(new RoleAuthority(Role.ADMINISTRATOR));
        }
        return roleAuthorities;
    }

}
