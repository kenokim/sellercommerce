package ssg.com.sellercommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ssg.com.sellercommerce.domain.Company;
import ssg.com.sellercommerce.service.CompanyService;
import ssg.com.sellercommerce.web.CompanyCreateDto;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class CompanyControllerTest {
    /**
     * Controller 검증 로직 테스트
     */
    @Autowired private CompanyController companyController;
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @MockBean private CompanyService companyService;

    private final Long companyId = 1000000000L;
    private final Long businessNumber = 1000000000L;
    private final String companyName = "hello";
    private final String phoneNumber = "01012345678";

    private final Long longPhoneNumber = 1012345678L;
    private final String address = "Hello";


    @BeforeEach
    public void setup() {
        Company company = Company.create(companyName, businessNumber, longPhoneNumber, address);
        when(companyService.register(companyName, businessNumber, longPhoneNumber, address))
                .thenReturn(companyId);
        when(companyService.registerAndReturnEntity(anyString(), anyLong(), anyLong(), anyString()))
                .thenReturn(company);
    }

    @ParameterizedTest
    @ValueSource(longs = { 1000000000L, 3000000000L })
    public void CompanyCreate_ValidInput_ReturnsOK(Long businessNumber) throws Exception {
        this.mockMvc.perform(post("/companies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(CompanyCreateDto.of(companyName, businessNumber, phoneNumber, address))))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @ValueSource(strings = { "021234567", "01012345678" })
    public void CompanyCreate_ValidInput2_ReturnsOK(String phoneNumber) throws Exception {
        this.mockMvc.perform(post("/companies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(CompanyCreateDto.of(companyName, businessNumber, phoneNumber, address))))
                .andExpect(status().isOk());
    }


    @ParameterizedTest
    @ValueSource(longs = { 10L, 10000000000L })
    public void CompanyCreate_InvalidBusinessNumber_ReturnsBadRequest(Long businessNumber) throws Exception {
        this.mockMvc.perform(post("/companies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(CompanyCreateDto.of(companyName, businessNumber, phoneNumber, address))))
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @ValueSource(strings = { "02123456", "010123456789" })
    public void CompanyCreate_InvalidPhoneNumber_ReturnsBadRequest(String phoneNumber) throws Exception {
        this.mockMvc.perform(post("/companies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(CompanyCreateDto.of(companyName, businessNumber, phoneNumber, address))))
                .andExpect(status().isBadRequest());
    }

}