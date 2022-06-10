package ssg.com.sellercommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ssg.com.sellercommerce.domain.Contract;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ContractRepository extends JpaRepository<Contract, Long>, ContractRepositorySupp {
    List<Contract> findAllByCompanyIdAndCurrentTime(Long companyId, LocalDateTime curr);
    Optional<Contract> findFirstByCompanyIdAndStartAtAndEndAt(Long companyId, LocalDateTime startAt, LocalDateTime endAt);

    @Query("select c from Contract as c")
    List<Contract> findAllByCompanyIdAndStartAtBeforeNowAndEndAtAfterNow(Long companyId, LocalDateTime now);
}
