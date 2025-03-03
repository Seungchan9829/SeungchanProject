package ksc.ts.service;

import ksc.ts.model.User;
import ksc.ts.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public User loadUserByUsername(String userEmail) throws UsernameNotFoundException {

        return userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException(userEmail));
    }
}

