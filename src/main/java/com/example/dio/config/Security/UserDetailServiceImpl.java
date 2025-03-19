package com.example.dio.config.Security;

import com.example.dio.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      return    userRepository.findByEmail(username)
                 .map(this::createAuthUser)
                .orElseThrow(()-> new UsernameNotFoundException("Failed to authenticate user,user not found !!"));
    }

    public UserDetails createAuthUser(com.example.dio.model.User user){
        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .build();

    }
}
