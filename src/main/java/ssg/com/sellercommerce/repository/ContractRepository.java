package ssg.com.sellercommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssg.com.sellercommerce.domain.Contract;

public interface ContractRepository extends JpaRepository<Contract, Long> {
}
