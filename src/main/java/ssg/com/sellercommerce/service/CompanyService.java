package ssg.com.sellercommerce.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssg.com.sellercommerce.domain.Company;
import ssg.com.sellercommerce.exception.IllegalRequestException;
import ssg.com.sellercommerce.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final ItemService itemService;

    /**
     * 업체 등록 메소드
     * 상품에 셋팅되었는지, 이미 등록된 업체명은 아닌지 검증한 후 업체를 생성합니다.
     */
    @Transactional
    public Long register(String companyName, Long businessNumber, Long phoneNumber, String address) {
        Company company = registerAndReturnEntity(companyName, businessNumber, phoneNumber, address);
        return company.getId();
    }

    @Transactional
    public Company registerAndReturnEntity(String companyName, Long businessNumber, Long phoneNumber, String address) {
        if (!itemService.isValidCompanyName(companyName)) {
            throw new IllegalRequestException("상품에 셋팅되지 않은 업체명입니다.");
        }
        Optional<Company> findCompany = companyRepository.findByCompanyName(companyName);
        if (findCompany.isPresent()) {
            throw new IllegalRequestException("이미 등록된 업체입니다.");
        }
        Company company = Company.create(companyName, businessNumber, phoneNumber, address);
        companyRepository.save(company);
        return company;
    }

    /**
     * 업체 업데이트 메소드
     * 이미 등록된 업체가 맞는지, 업체명의 업데이트를 시도하진 않는지 검증한 후 업데이트합니다.
     */
    @Transactional
    public Company update(Long companyId, String companyName, Long businessNumber, Long phoneNumber, String address) {
        Optional<Company> company = companyRepository.findById(companyId);
        if (company.isEmpty()) {
            throw new IllegalRequestException("존재하지 않는 업체명입니다.");
        }
        if (company.isPresent() && !company.get().getCompanyName().equals(companyName)) {
            throw new IllegalRequestException("업체명은 업데이트할 수 없습니다.");
        }
        Company entity = company.get();
        entity.update(businessNumber, phoneNumber, address);
        companyRepository.save(entity);
        return entity;
    }

    public Company findByIdOrThrow(Long companyId) {
        return companyRepository.findById(companyId).orElseThrow(() -> new IllegalRequestException("존재하지 않는 업체입니다."));
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }
}
