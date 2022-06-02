package ssg.com.sellercommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssg.com.sellercommerce.domain.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
