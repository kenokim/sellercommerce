package ssg.com.sellercommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssg.com.sellercommerce.domain.Billing;

public interface BillingRepository extends JpaRepository<Billing, Long> {
}
