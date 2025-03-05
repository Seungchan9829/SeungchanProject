package ksc.ts.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(unique = true)
    private String accountNumber;

    @Column
    private BigDecimal accountBalance;

    @Column
    private String accountPassword;

    @OneToMany(mappedBy = "account")
    List<MoneyTransaction> moneyTransactions = new ArrayList<MoneyTransaction>();

    @Version
    private Long version;

}
