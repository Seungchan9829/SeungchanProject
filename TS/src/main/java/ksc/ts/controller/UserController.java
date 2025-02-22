package ksc.ts.controller;

import ksc.ts.dto.UserRequest;
import ksc.ts.dto.UserResponse;
import ksc.ts.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    // RequestBody -> 클라이언트가 보낸 JSON DATA를 타입에 맞게 변환해준다

    @PostMapping("/sign-up")
    public ResponseEntity<UserResponse> signUp(@RequestBody UserRequest userRequest) {
        UserResponse response = userService.createUser(userRequest);

        return ResponseEntity.ok(response);
    }
    // 1.컨트롤러  UserSignUpResponse 객체 반환
    // 2. JSON(UserSignUpResponse)를 반환해야함
    // 3. Jakson이 객체의 필드를 Json으로 변환하는 과정에서 getter 찾음. (getter, setter, builder ???)
    // 직렬화 ? 역직렬화 ?
    // 회원가입 로직, 회원 수정 로직 DTO

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long userId) {
        UserResponse response = userService.getUser(userId);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long userId, @RequestBody UserRequest request) {
        UserResponse response = userService.updateUser(userId, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);

        return ResponseEntity.noContent().build();


    }



}
