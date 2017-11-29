package cn.cloud.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/10/5.
 */

@Service
public class AuthUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        cn.cloud.auth.domain.User user = userService.findOne(Long.valueOf(id));

        if (user == null) {
            throw new UsernameNotFoundException(id);
        }

        return new User(id, user.getPassword() , getGrantedAuthorities());
    }


    public static List<GrantedAuthority> getGrantedAuthorities() {
        List<String> roles =  new ArrayList<>();
        roles.add("ROLE_USER");
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(r -> authorities.add(new SimpleGrantedAuthority(r)));

        return authorities;
    }

}
