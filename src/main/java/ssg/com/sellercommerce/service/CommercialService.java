package ssg.com.sellercommerce.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssg.com.sellercommerce.domain.*;
import ssg.com.sellercommerce.exception.IllegalRequestException;
import ssg.com.sellercommerce.repository.CommercialBillingRepository;
import ssg.com.sellercommerce.repository.CommercialRepository;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommercialService {
    private final CommercialRepository commercialRepository;
    private final CompanyService companyService;

    private final ItemService itemService;

    private final ContractService contractService;
    private final CommercialBillingRepository commercialBillingRepository;

    /**
     * 광고 생성 메소드
     * 상품이 업체가 등록한 것인지, 계약이 요청 시점에 유효한지 검증한 후 광고를 생성합니다.
     */
    @Transactional
    public Long createCommercial(Long companyId, Long itemId, Integer bid) {
        Company company = companyService.findByIdOrThrow(companyId);
        Item item = itemService.findByIdOrThrow(itemId);
        List<Contract> contracts = contractService.findByCompanyIdWhereContractTermValid(companyId);
        if (contracts.size() < 1) {
            throw new IllegalRequestException("계약생성 및 계약기간이 유효한 업체가 아닙니다.");
        }
        if (!item.getCompanyName().equals(company.getCompanyName())) {
            throw new IllegalRequestException("상품이 해당 업체의 것이 아닙니다.");
        }
        Commercial commercial = Commercial.create(company, item, bid);
        commercialRepository.save(commercial);
        return commercial.getId();
    }

    /**
     * number 개의 입찰가 순의 재고가 남은 광고를 조회합니다.
     */
    public List<Commercial> displayCommercials(Integer number) {
        return commercialRepository.displayCommercials(3);
    }

    public Commercial findByIdOrThrow(Long commercialId) {
        return commercialRepository.findById(commercialId).orElseThrow(() -> new IllegalRequestException("존재하지 않는 광고입니다."));
    }

    /**
     * 광고 과금 메소드
     * 광고에 대한 과금을 생성합니다.
     */
    @Transactional
    public Long clickBilling(Long commercialId, LocalDateTime clickedAt) {
        Commercial commercial = findByIdOrThrow(commercialId);
        CommercialBilling billing = CommercialBilling.create(commercial, clickedAt, commercial.getBid());
        commercialBillingRepository.save(billing);
        return billing.getId();
    }

    @Transactional
    public Long clickBilling(Long commercialId) {
        LocalDateTime clickedAt = LocalDateTime.now();
        return clickBilling(commercialId, clickedAt);
    }
}
