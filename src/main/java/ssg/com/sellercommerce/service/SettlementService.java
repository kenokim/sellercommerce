package ssg.com.sellercommerce.service;

import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssg.com.sellercommerce.domain.Commercial;
import ssg.com.sellercommerce.domain.Company;
import ssg.com.sellercommerce.domain.Item;
import ssg.com.sellercommerce.domain.Settlement;
import ssg.com.sellercommerce.repository.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SettlementService {
    private final SettlementRepository settlementRepository;
    private final CommercialRepository commercialRepository;
    private final CommercialBillingRepository commercialBillingRepository;

    private final CompanyRepository companyRepository;
    private final ItemRepository itemRepository;

    private final CompanyService companyService;

    public List<Settlement> findAll() {
        return settlementRepository.findAll();
    }


    /**
     * 정산 메소드
     * from 부터 to 까지 생성된 광고과금을 집계하여 정산 데이터를 생성합니다.
     */
    @Transactional
    public void processSettlements(LocalDateTime from, LocalDateTime to) {
        log.info("정산 작업을 시작합니다.");
        List<Commercial> commercials = commercialRepository.findAll();
        for (Commercial commercial : commercials) {
            try {
                log.info("ID: {} 광고의 정산을 시작합니다.", commercial.getId());
                Company company = commercial.getCompany();
                Item item = commercial.getItem();
                Tuple t = commercialBillingRepository.aggregateSettlements(commercial.getId(), from, to);
                Long clickCount = t.get(0, Long.class);
                Integer totalBilling = t.get(1, Integer.class);
                if (totalBilling == null) {
                    totalBilling = 0;
                }
                Settlement settlement = Settlement
                        .builder()
                        .company(company)
                        .commercial(commercial)
                        .item(item)
                        .clickCount(clickCount)
                        .clickDate(LocalDateTime.now())
                        .totalBilling(totalBilling)
                        .build();
                settlementRepository.save(settlement);
            } catch (Exception e) {
                log.error(e.getMessage());
                log.error("ID: {} 광고의 정산에 실패하였습니다.", commercial.getId());
            }
        }
    }
}
