package ksc.ts.repository;

import ksc.ts.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// JPA Repo 사용 -> 쿼리 없이 기본적인 DB 조작 가능
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Optional은 객체를 감싸는 역할을 한다. -> 기본형 long은 사용불가능
    // JPA는 데이터베이스를 ID 값을 nullable하게 관리 가능 -> 왜

    // findById를 호출했을 떄 ID가 존재하지 않으면 null을 리턴한다.
    // 이때 long은 null 값을 가질 수 없지만, Long은 객체라 null 값을 가질 수 있다

    // Optional -> null
    Optional<User> findById(Long id);

    // 값을 찾지 못하면 Optional.empty()
    Optional<User> findByUserEmail(String userEmail);

    Optional<User> findByUserName(String userName);

}
