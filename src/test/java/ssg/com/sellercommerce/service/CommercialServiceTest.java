package ssg.com.sellercommerce.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ssg.com.sellercommerce.domain.Commercial;
import ssg.com.sellercommerce.domain.Company;
import ssg.com.sellercommerce.domain.Contract;
import ssg.com.sellercommerce.domain.Item;
import ssg.com.sellercommerce.exception.IllegalRequestException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
class CommercialServiceTest {
    @Autowired private CommercialService commercialService;

    @MockBean private CompanyService companyService;
    @MockBean private ItemService itemService;
    @MockBean private ContractService contractService;

    private final String companyName = "이상해씨샵";
    private final Long businessNumber = 1000000000L;
    private final Long phoneNumber = 1012345678L;
    private final String address = "hello";
    private final Long companyId = 1000000000L;
    private final String itemName = "벌레 이상해씨";
    private final String itemName2 = "이상한 이상해씨";
    private final Integer price = 1000000;
    private final Integer price2 = 10000;
    private final Integer stockQuantity = 1;
    private final Integer stockQuantity2 = 0;
    private final Long itemId = 100000000L;
    private final Long itemId2 = 100000001L;
    private final Integer bid = 1000000;


    private Company company;
    private Item item;
    private Item item2;
    private Contract contract;


    @BeforeEach
    public void setup() {
        company = Company.create(companyName, businessNumber, phoneNumber, address);
        when(companyService.findByIdOrThrow(companyId)).thenReturn(company);

        item = Item.createItem(companyName, itemName, price, stockQuantity);
        when(itemService.findByIdOrThrow(itemId)).thenReturn(item);

        item2 = Item.createItem(companyName, itemName2, price2, stockQuantity2);
        when(itemService.findByIdOrThrow(itemId2)).thenReturn(item2);

        contract = Contract.create(company, LocalDateTime.now().minusHours(1), LocalDateTime.now().plusHours(1));
        when(contractService.findByCompanyIdWhereContractTermValid(companyId)).thenReturn(Arrays.asList(contract));

    }

    @Test
    public void CommercialBid_Success() {
        commercialService.createCommercial(companyId, itemId, bid);
    }

    @Test
    public void CommercialBid_NoContract_Exception() {
        Long illegalCompanyId = 150000000L;
        when(contractService.findByCompanyIdWhereContractTermValid(illegalCompanyId)).thenThrow(new IllegalRequestException("ERROR"));
        assertThrows(IllegalRequestException.class, () -> {
            commercialService.createCommercial(illegalCompanyId, itemId, bid);
        });
    }

    @Test
    public void CommercialBid_ItemNotRegisteredByCompany_Fail() {
        String illegalCompanyName = "다른기업";
        Item otherItem = Item.createItem(illegalCompanyName, itemName2, 10000, 1);
        Long otherItemId = 200000000L;
        when(itemService.findByIdOrThrow(otherItemId)).thenReturn(otherItem);

        assertThrows(IllegalRequestException.class, () -> {
            commercialService.createCommercial(companyId, otherItemId, 10000);
        });
    }

    public void createCommercials() {
        commercialService.createCommercial(companyId, itemId, 25000);
        commercialService.createCommercial(companyId, itemId, 15000);
        commercialService.createCommercial(companyId, itemId, 35000);
        commercialService.createCommercial(companyId, itemId2, 45000);
        commercialService.createCommercial(companyId, itemId, 500);
    }
    @Test
    public void CommercialDisplay_Below_3 () {
        //commercialService.createCommercial(companyId, itemId, 25000);
        //commercialService.createCommercial(companyId, itemId, 15000);
        //commercialService.createCommercial(companyId, itemId, 35000);
        //commercialService.createCommercial(companyId, itemId2, 45000);
        createCommercials();

        List<Commercial> commercials = commercialService.displayCommercials(3);
        assertThat(commercials.size()).isEqualTo(3);

    }

    @Test
    public void CommercialDisplay_DescByBid() {
        commercialService.createCommercial(companyId, itemId, 25000);
        commercialService.createCommercial(companyId, itemId, 15000);
        commercialService.createCommercial(companyId, itemId, 500);
        commercialService.createCommercial(companyId, itemId, 35000);

        List<Commercial> commercials = commercialService.displayCommercials(3);
        commercials.stream().forEach(c -> {
            if (c.getBid().equals(500)) {
                fail();
            }
        });
    }
}

















