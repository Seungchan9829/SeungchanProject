package ksc.ts.repository;

import ksc.ts.model.Trades;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trades, Long> {
}
