package ksc.ts.service;


import jakarta.transaction.Transactional;
import ksc.ts.dto.UserRequest;
import ksc.ts.dto.UserResponse;
import ksc.ts.exception.UserNotFoundException;
import ksc.ts.model.User;
import ksc.ts.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserResponse createUser(UserRequest request) {

        // 중복체크 유효성 체크

        // 빌더 사용? requestDTO -> User -> responseDTO
        User user = User.builder()
                .userEmail(request.getUserEmail())
                .userName(request.getUserName())
                .password(request.getPassword())
                .build();

        userRepository.findByUserEmail(user.getUserEmail()).ifPresent(presentUser -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 존재하는 이메일입니다.");
        });

        User savedUser = userRepository.save(user);

        return UserResponse.builder()
                .id(savedUser.getId())
                .userEmail(savedUser.getUserEmail())
                .userName(savedUser.getName())
                .build();

    }


    @Transactional
    public UserResponse updateUser(Long userId, UserRequest request) {

        User findUser = userRepository.findById(userId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."));

        findUser.setUserName(request.getUserName());
        findUser.setUserEmail(request.getUserEmail());
        findUser.setPassword(request.getPassword());


        User savedUser = userRepository.save(findUser);

        return UserResponse.builder()
                .id(savedUser.getId())
                .userEmail(savedUser.getUserEmail())
                .userName(savedUser.getName())
                .build();

    }


    @Transactional
    public void deleteUser(Long userId) {

        User findUser = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."));

        userRepository.delete(findUser);
    };

    @Transactional
    public UserResponse getUser(Long userId) {
        User findUser = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."));

        return UserResponse.builder()
                .id(findUser.getId())
                .userEmail(findUser.getUserEmail())
                .userName(findUser.getName())
                .build();
    }




}

