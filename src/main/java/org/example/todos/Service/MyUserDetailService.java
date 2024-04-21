package org.example.todos.Service;

import lombok.RequiredArgsConstructor;
import org.example.todos.Api.ApiException;
import org.example.todos.Model.User;
import org.example.todos.Repoistory.AuthRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailService implements UserDetailsService {//2

   private final AuthRepository authRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = authRepository.findUserByUsername(username); //4

        if(user==null){
            throw new ApiException("wrong username or password");

        }
        return user;
    }
}
