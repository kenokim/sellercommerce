package ssg.com.sellercommerce.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssg.com.sellercommerce.domain.Commercial;
import ssg.com.sellercommerce.domain.CommercialBilling;
import ssg.com.sellercommerce.domain.Company;
import ssg.com.sellercommerce.domain.Item;
import ssg.com.sellercommerce.exception.IllegalRequestException;
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

    @Transactional
    public Long createCommercial(Long companyId, Long itemId, Integer bid) {
        Company company = companyService.findByIdOrThrow(companyId);
        Item item = itemService.findByIdOrThrow(itemId);
        if (!item.getCompanyName().equals(company.getCompanyName())) {
            throw new IllegalRequestException("상품이 해당 업체의 것이 아닙니다.");
        }
        Commercial commercial = Commercial.create(company, item, bid);
        commercialRepository.save(commercial);
        return commercial.getId();
    }

    public List<Commercial> displayCommercials(Integer number) {
        return commercialRepository.findAll();
    }

    public Commercial findByIdOrThrow(Long commercialId) {
        return commercialRepository.findById(commercialId).orElseThrow(() -> new IllegalRequestException("존재하지 않는 광고입니다."));
    }

    @Transactional
    public Long clickBilling(Long commercialId, LocalDateTime clickedAt) {
        Commercial commercial = findByIdOrThrow(commercialId);
        CommercialBilling billing = CommercialBilling.create(commercial, clickedAt, commercial.getBid());
        return billing.getId();
    }

    @Transactional
    public Long clickBilling(Long commercialId) {
        LocalDateTime clickedAt = LocalDateTime.now();
        return clickBilling(commercialId, clickedAt);
    }
}
