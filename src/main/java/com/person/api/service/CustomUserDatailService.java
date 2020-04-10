package com.person.api.service;

import com.person.api.dto.OperatorRequestDTO;
import com.person.api.model.OperatorEntity;
import com.person.api.repository.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomUserDatailService implements UserDetailsService {

    private final OperatorRepository repository;

    @Autowired
    public CustomUserDatailService(OperatorRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        OperatorEntity operatorEntity = Optional.ofNullable(repository.fiddlyOperatorName(name)).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList("ROLE_GESTOR", "ROLE_ADMIN");
        List<GrantedAuthority> authorityListGestor = AuthorityUtils.createAuthorityList("ROLE_GESTOR");

        return new User(operatorEntity.getLogin(),operatorEntity.getPassword(),operatorEntity.isAdmin() ? authorityListAdmin : authorityListGestor);
    }
}
