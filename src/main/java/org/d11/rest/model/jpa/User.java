package org.d11.rest.model.jpa;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;

import org.d11.rest.model.jpa.converter.RoleAuthorityConverter;
import org.d11.rest.security.Role;
import org.d11.rest.security.RoleAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User extends D11RestEntity implements UserDetails {

    @Column(name = "email")
    @NotEmpty
    private String username;
    @Column(name = "encrypted_password")
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;
    @PositiveOrZero
    private int signInCount;
    @NotNull
    @PastOrPresent
    private LocalDateTime currentSignInAt;
    @NotNull
    @PastOrPresent
    private LocalDateTime lastSignInAt;
    @NotEmpty
    private String currentSignInIp;
    @NotEmpty
    private String lastSignInIp;

    @Column(name = "administrator")
    @Convert(converter = RoleAuthorityConverter.class)
    private List<RoleAuthority> roleAuthorities = new ArrayList<>();

    private static final long serialVersionUID = 985217709942293748L;

    protected User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSignInCount() {
        return signInCount;
    }

    public void setSignInCount(int signInCount) {
        this.signInCount = signInCount;
    }

    public LocalDateTime getCurrentSignInAt() {
        return currentSignInAt;
    }

    public void setCurrentSignInAt(LocalDateTime currentSignInAt) {
        this.currentSignInAt = currentSignInAt;
    }

    public LocalDateTime getLastSignInAt() {
        return lastSignInAt;
    }

    public void setLastSignInAt(LocalDateTime lastSignInAt) {
        this.lastSignInAt = lastSignInAt;
    }

    public String getCurrentSignInIp() {
        return currentSignInIp;
    }

    public void setCurrentSignInIp(String currentSignInIp) {
        this.currentSignInIp = currentSignInIp;
    }

    public String getLastSignInIp() {
        return lastSignInIp;
    }

    public void setLastSignInIp(String lastSignInIp) {
        this.lastSignInIp = lastSignInIp;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roleAuthorities;
    }

    public void addRole(Role role) {
        if (!hasRole(role)) {
            this.roleAuthorities.add(new RoleAuthority(role));
        }
    }

    public void removeRole(Role role) {
        for (RoleAuthority roleAuthority : this.roleAuthorities) {
            if (roleAuthority.getRole() == role) {
                this.roleAuthorities.remove(roleAuthority);
                break;
            }
        }
    }

    public boolean hasRole(Role role) {
        for (RoleAuthority roleAuthority : this.roleAuthorities) {
            if (roleAuthority.getRole() == role) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
