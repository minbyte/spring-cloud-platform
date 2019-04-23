package com.mindasoft.cloud.models.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: min
 * @date: 2019/4/23 9:10
 * @version: 1.0.0
 */
@Getter
@Setter
public class LoginUser implements UserDetails {

    private Long userId;

    private String username;

    private String password;

    private Boolean enabled;

    private Collection<String> roles;
    private Collection<String> permissions;

    /***
     * 权限重写
     */
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> collection = new HashSet<>();
        if (!CollectionUtils.isEmpty(roles)) {
            roles.parallelStream().forEach(role -> {
                collection.add(new SimpleGrantedAuthority("ROLE_" + role));
            });
        }

        if (!CollectionUtils.isEmpty(permissions)) {
            permissions.parallelStream().forEach(perm -> {
                collection.add(new SimpleGrantedAuthority(perm));
            });
        }

        return collection;
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
        return getEnabled();
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
