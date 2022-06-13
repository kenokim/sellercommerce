package ssg.com.sellercommerce.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ssg.com.sellercommerce.domain.Item;
import ssg.com.sellercommerce.repository.ItemRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired private ItemService itemService;

    @Autowired private ItemRepository itemRepository;

    private final String companyName = "신세계몰";
    private final String itemName = "굿즈";
    private final Integer price = 1000000;
    private final Integer stockQuantity = 1;

    @BeforeEach
    public void setup() {
        Item item = Item.createItem(companyName, itemName, price, stockQuantity);
        itemRepository.save(item);
    }

    @Test
    public void FindByCompanyName_Success() {
        assertThat(itemService.isValidCompanyName(companyName)).isTrue();
    }

    @Test
    public void FindByCompanyName_Fail() {
        assertThat(itemService.isValidCompanyName("신세계백화점")).isFalse();
    }
}