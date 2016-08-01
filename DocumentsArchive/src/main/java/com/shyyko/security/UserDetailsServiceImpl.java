package com.shyyko.security;

import com.shyyko.entity.UserEntity;
import com.shyyko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

/**
 * User: shyykoserhiy
 */
@Service("userDetailsServiceImpl")
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userService.findUserByLogin(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("No such user : " + username);
        }
        if (userEntity.getRole() == null) {
            throw new UsernameNotFoundException("User " + username + " has no authority");
        }
        return buildUserDetails(userEntity);
    }

    private static UserDetails buildUserDetails(UserEntity userEntity) {
        String username = userEntity.getUsername();
        String password = userEntity.getPassword();
        boolean enabled = true;
        boolean accountNonExpired = enabled;
        boolean credentialsNonExpired = enabled;
        boolean accountNonLocked = enabled;

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(userEntity.getRole().name()));
        return new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
