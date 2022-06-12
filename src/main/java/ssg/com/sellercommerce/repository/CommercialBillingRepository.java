package ssg.com.sellercommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssg.com.sellercommerce.domain.CommercialBilling;

public interface CommercialBillingRepository extends JpaRepository<CommercialBilling, Long>, CommercialBillingRepositorySupp {
}
