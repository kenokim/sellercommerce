package ssg.com.sellercommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssg.com.sellercommerce.domain.Settlement;

public interface SettlementRepository extends JpaRepository<Settlement, Long>, SettlementRepositorySupp {
}
