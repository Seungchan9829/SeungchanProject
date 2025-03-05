package ksc.ts.repository;

import ksc.ts.model.MoneyTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoneyTransactionRepository extends JpaRepository<MoneyTransaction, Long> {
}
