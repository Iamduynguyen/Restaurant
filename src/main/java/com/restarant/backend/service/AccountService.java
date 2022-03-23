package com.restarant.backend.service;

import com.restarant.backend.domain.Account;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Map;
import java.util.stream.Collectors;

public class AccountService {

//    public Account getUserFromAuthentication(AbstractAuthenticationToken authToken) {
//        Map<String, Object> attributes;
////        if (authToken instanceof OAuth2AuthenticationToken) {
////            attributes = ((OAuth2AuthenticationToken) authToken).getPrincipal().getAttributes();
////        } else
//        if (authToken instanceof JwtAuthenticationToken) {
//            attributes = ((JwtAuthenticationToken) authToken).getTokenAttributes();
//        } else {
//            throw new IllegalArgumentException("AuthenticationToken is not OAuth2 or JWT!");
//        }
//        User user = getUser(attributes);
//        user.setAuthorities(
//                authToken
//                        .getAuthorities()
//                        .stream()
//                        .map(GrantedAuthority::getAuthority)
//                        .map(authority -> {
//                            Authority auth = new Authority();
//                            auth.setName(authority);
//                            return auth;
//                        })
//                        .collect(Collectors.toSet())
//        );
//
//    }

    public String getAccountByAuthority(Principal principal){
        String login = principal.toString();
        System.out.println(login);
        return login;
    }
}
