package ssg.com.sellercommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssg.com.sellercommerce.domain.Contract;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    Optional<Contract> findFirstByCompanyIdAndStartAtAndEndAt(Long companyId, LocalDateTime startAt, LocalDateTime endAt);
}
