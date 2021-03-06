package ssg.com.sellercommerce.utils;

import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ssg.com.sellercommerce.domain.Company;
import ssg.com.sellercommerce.domain.Item;
import ssg.com.sellercommerce.repository.CommercialBillingRepository;
import ssg.com.sellercommerce.repository.CompanyRepository;
import ssg.com.sellercommerce.repository.ItemRepository;
import ssg.com.sellercommerce.repository.SettlementRepository;
import ssg.com.sellercommerce.service.CommercialService;
import ssg.com.sellercommerce.service.ContractService;
import ssg.com.sellercommerce.service.SettlementService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 테스트 데이터 생성 클래스
 */
@Profile("!test")
@Component
@Transactional
@RequiredArgsConstructor
public class DataGenerator implements CommandLineRunner {

    private final ItemRepository itemRepository;

    private final CompanyRepository companyRepository;

    private final ContractService contractService;

    private final CommercialService commercialService;

    private final SettlementRepository settlementRepository;

    private final CommercialBillingRepository commercialBillingRepository;

    private final SettlementService settlementService;

    @Override
    public void run(String... args) throws Exception {
        generateItems();
        generateCompanies();
        generateContracts();
        generateCommercials();
        generateBillings();
    }

    private void generateItems() {
        saveItem("이마트", "허쉬 초코멜로쿠키 45g", 600, 10);
        saveItem("신세계백화점", "크리스피롤 12 곡 180g", 2800, 5);
        saveItem("삼성전자", "갤럭시 22 자급제모델", 1200000, 0);
        saveItem("이마트", "말랑카우 핸드워시 250ml", 2600, 6);
        saveItem("신세계백화점", "삼립 미니꿀호떡 322g", 1200, 4);
        saveItem("이마트", "피코크 어랑손만두 어랑만두 700g", 6400, 2);
        saveItem("신세계백화점", "빼빼로바 아몬드아이스크림 4 입", 2800, 1);
        saveItem("이마트", "피코크 에이 클래스 우유 900ml", 2500, 3);
        saveItem("이마트", "아삭달콤 방울토마토 500g", 4500, 0);
        saveItem("이마트", "[롯데삼강] 돼지바 (70ml*6 입)", 3000, 1);
        saveItem("나이키", "나이키 덩크로우 흰검", 129000, 4);
        saveItem("스타벅스", "이달의 원두 500g", 18500, 4);
        saveItem("스타벅스", "아쿠아 머그", 23000, 7);
        saveItem("삼성전자", "삼성전자 43 인치 스마트모니터", 480000, 2);
        saveItem("나이키", "나이키 헤리티지 스우시 캡", 25000, 5);
        saveItem("신세계몰", "유세린 수분크림", 8415, 3);
        saveItem("신세계몰", "AU테크 전기자전거", 439000, 4);
        saveItem("신세계몰", "코디 티슈", 20800, 8);
        saveItem("신세계몰", "올리비아데코 이불", 17750, 3);

    }

    private void generateCompanies() {
        saveCompany("신세계몰", 1000000000L, 215773419L, "서울특별시 종로구 우정국로 26 ");
        saveCompany("이마트", 1000000000L, 1234567L, "hello");
        saveCompany("신세계백화점", 1000000000L, 1234567L, "hello");
    }

    private void saveItem(String companyName, String itemName, int price, int stockQuantity) {
        Item item = Item.createItem(companyName, itemName, price, stockQuantity);
        itemRepository.save(item);
    }

    private void saveCompany(String companyName, Long businessName, Long phoneNumber, String address) {
        Company company = Company.create(companyName, businessName, phoneNumber, address);
        companyRepository.save(company);
    }

    private void generateContracts() {
        contractService.createContract(1000000051L);
        contractService.createContract(1000000052L);
        contractService.createContract(1000000053L);

    }

    private void generateCommercials() {
        commercialService.createCommercial(1000000053L, 1000000001L, 10000);
        commercialService.createCommercial(1000000053L, 1000000004L, 15000);
        commercialService.createCommercial(1000000053L, 1000000006L, 20000);
        commercialService.createCommercial(1000000052L, 1000000008L, 25000);
    }

    private void generateBillings() {
        for (int i = 0; i < 11; i++) {
            commercialService.clickBilling(1000000151L);
        }
        for (int i = 0; i < 11; i++) {
            commercialService.clickBilling(1000000151L, LocalDateTime.now().minusDays(1));
        }
    }
}
