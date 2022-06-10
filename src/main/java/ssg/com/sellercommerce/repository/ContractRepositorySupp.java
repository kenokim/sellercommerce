package ssg.com.sellercommerce.repository;

import ssg.com.sellercommerce.domain.Contract;

import java.time.LocalDateTime;
import java.util.List;

public interface ContractRepositorySupp {
    List<Contract> findAllByCompanyIdAndCurrentTime(Long companyId, LocalDateTime curr);
}
