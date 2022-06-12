package ssg.com.sellercommerce.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssg.com.sellercommerce.domain.Company;
import ssg.com.sellercommerce.domain.Contract;
import ssg.com.sellercommerce.exception.IllegalRequestException;
import ssg.com.sellercommerce.repository.ContractRepository;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContractService {

    private final CompanyService companyService;

    private final ContractRepository contractRepository;

    @Transactional
    public Long createContract(Long companyId) {
        LocalDateTime startAt = LocalDateTime.now();
        LocalDateTime endAt = startAt.plusYears(1);
        return createContract(companyId, startAt, endAt);
    }

    @Transactional
    public Long createContract(Long companyId, LocalDateTime startAt, LocalDateTime endAt) {
        // 존재하는 업체여야 합니다
        Company company = companyService.findByIdOrThrow(companyId);
        // 계약기간이 중복되지 않아야 합니다
        if (!contractRepository.findAllByCompanyIdAndCurrentTime(companyId, startAt).isEmpty()) {
            throw new IllegalRequestException("기간이 중복된 계약이 있습니다.");
        }
        Contract contract = Contract.create(company, startAt, endAt);
        contractRepository.save(contract);
        return contract.getId();
    }

    public List<Contract> findByCompanyIdWhereContractTermValid(Long companyId, LocalDateTime now) {
        return contractRepository.findAllByCompanyIdAndStartAtBeforeNowAndEndAtAfterNow(companyId, now);
    }

    public List<Contract> findByCompanyIdWhereContractTermValid(Long companyId) {
        LocalDateTime now = LocalDateTime.now();
        return findByCompanyIdWhereContractTermValid(companyId, now);
    }

    public List<Contract> findAll() {
        return contractRepository.findAll();
    }
}
