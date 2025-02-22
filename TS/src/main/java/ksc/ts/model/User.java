package ksc.ts.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(name = "unique_email", columnNames = "user_email")
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    // JsonProperty를 이용해보자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userName;

    @Column
    private String userEmail;

    @Column
    private String password;

    @OneToMany(mappedBy = "user")
    List<Account> accounts = new ArrayList<Account>();



}
