package ksc.ts.service;

import jakarta.transaction.Transactional;
import ksc.ts.dto.AuthenticationRequest;
import ksc.ts.dto.AuthenticationResponse;
import ksc.ts.dto.RegisterRequest;
import ksc.ts.model.Role;
import ksc.ts.model.User;
import ksc.ts.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        User user = User.builder()
                .userEmail(registerRequest.getUserEmail())
                .userName(registerRequest.getUserName())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();

        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 존재하는 유저입니다.");
        } catch (Exception e) {
            throw new RuntimeException("회원 가입 중 오류 발생", e);
        }

        String jwtToken = jwtService.generateToken(user);


        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUserEmail(),
                        authenticationRequest.getPassword()
                )
        );
        User user = userRepository.findByUserEmail(authenticationRequest.getUserEmail()).orElse(null);
        String jwtToken = jwtService.generateToken(user);


        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
