package ssg.com.sellercommerce.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;
import ssg.com.sellercommerce.exception.IllegalRequestException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class CompanyServiceTest {
    @MockBean private ItemService itemService;

    @Autowired private CompanyService companyService;

    private final String companyName = "이상해씨샵";
    private final Long businessNumber = 1000000000L;
    private final Long phoneNumber = 1012345678L;
    private final String address = "hello";

    @Test
    public void CreateCompany_Success() {
        when(itemService.isValidCompanyName(companyName))
                .thenReturn(true);
        assertThat(companyService.register(companyName, businessNumber, phoneNumber, address)).isGreaterThan(1000000000L).isNotNull();
    }

    @Test
    public void CreateCompany_Fail() {
        when(itemService.isValidCompanyName(companyName))
                .thenReturn(false);
        assertThrows(IllegalRequestException.class, () -> companyService.register(companyName, businessNumber, phoneNumber, address));
    }

}