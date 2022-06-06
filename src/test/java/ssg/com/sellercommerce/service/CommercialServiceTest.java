package ssg.com.sellercommerce.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;
import ssg.com.sellercommerce.domain.Company;
import ssg.com.sellercommerce.domain.Item;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class CommercialServiceTest {
    @Autowired private CommercialService commercialService;

    @MockBean private CompanyService companyService;
    @MockBean private ItemService itemService;

    private final String companyName = "이상해씨샵";
    private final Long businessNumber = 1000000000L;
    private final Long phoneNumber = 1012345678L;
    private final String address = "hello";
    private final Long companyId = 1000000000L;
    private final String itemName = "벌레 이상해씨";
    private final Integer price = 1000000;
    private final Integer stockQuantity = 1;
    private final Long itemId = 100000000L;
    private final Integer bid = 1000000;

    @BeforeEach
    public void setup() {
        Company company = Company.create(companyName, businessNumber, phoneNumber, address);
        Item item = Item.createItem(companyName, itemName, price, stockQuantity);
        when(companyService.findByIdOrThrow(companyId)).thenReturn(company);
        when(itemService.findByIdOrThrow(itemId)).thenReturn(item);
    }

    @Test
    public void CommercialBid_Success() {
        commercialService.createCommercial(companyId, itemId, bid);
    }
}

















