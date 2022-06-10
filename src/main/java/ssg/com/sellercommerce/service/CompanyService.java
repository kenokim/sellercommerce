package ssg.com.sellercommerce.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssg.com.sellercommerce.domain.Company;
import ssg.com.sellercommerce.exception.IllegalRequestException;
import ssg.com.sellercommerce.repository.CompanyRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final ItemService itemService;

    @Transactional
    public Long register(String companyName, Long businessNumber, Long phoneNumber, String address) {
        if (!itemService.isValidCompanyName(companyName)) {
            throw new IllegalRequestException("상품에 셋팅되지 않은 업체명입니다.");
        }
        Company company = Company.create(companyName, businessNumber, phoneNumber, address);
        companyRepository.save(company);
        return company.getId();
    }

    public Company findByIdOrThrow(Long companyId) {
        return companyRepository.findById(companyId).orElseThrow(() -> new IllegalRequestException("존재하지 않는 업체입니다."));
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }
}
