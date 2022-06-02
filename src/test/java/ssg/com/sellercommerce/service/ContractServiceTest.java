package ssg.com.sellercommerce.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;
import ssg.com.sellercommerce.domain.Company;
import ssg.com.sellercommerce.domain.Contract;
import ssg.com.sellercommerce.exception.IllegalRequestException;
import ssg.com.sellercommerce.repository.CompanyRepository;
import ssg.com.sellercommerce.repository.ContractRepository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
public class ContractServiceTest {
    @MockBean
    private ItemService itemService;

    @Autowired
    private CompanyService companyService;

    @Autowired private CompanyRepository companyRepository;

    @Autowired private ContractRepository contractRepository;

    @Autowired private ContractService contractService;

    private final String companyName = "이상해씨샵";
    private final Long businessNumber = 1000000000L;
    private final Long phoneNumber = 1012345678L;
    private final String address = "hello";

    private Long companyId;

    private final Long contractId = 100000000L;

    @BeforeEach
    public void setup() {
        Company company = Company.create(companyName, businessNumber, phoneNumber, address);
        companyRepository.save(company);
        this.companyId = company.getId();
        LocalDateTime startAt = LocalDateTime.of(2022, 06, 02, 19, 11);
        LocalDateTime endAt = startAt.plusYears(1);
        Contract contract = Contract.create(company, startAt, endAt);
        contractRepository.save(contract);
        when(contractService.createContract(companyId))
                .thenReturn(contractId);
    }

    @Test
    public void DuplicateInterval_Fail() {
        LocalDateTime startAt = LocalDateTime.of(2022, 06, 02, 19, 12);
        assertThrows(IllegalRequestException.class, () -> contractService.createContract(companyId, startAt, startAt.plusYears(1)));
    }

}