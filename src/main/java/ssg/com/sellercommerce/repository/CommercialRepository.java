package ssg.com.sellercommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssg.com.sellercommerce.domain.Commercial;

public interface CommercialRepository extends JpaRepository<Commercial, Long>, CommercialRepositorySupp {
}
