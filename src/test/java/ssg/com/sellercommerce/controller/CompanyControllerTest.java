package ssg.com.sellercommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ssg.com.sellercommerce.web.CompanyCreateDto;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class CompanyControllerTest {
    @Autowired private CompanyController companyController;
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    private final String validCompanyName = "hello";
    private final String validPhoneNumber = "01012345678";
    private final String validAddress = "Hello";

    @ParameterizedTest
    @ValueSource(longs = { 10L, 10000000000L })
    public void CompanyCreate_InvalidBusinessNumber_ReturnsBadRequest(Long businessNumber) throws Exception {
        this.mockMvc.perform(post("/company")
                .content(objectMapper.writeValueAsString(CompanyCreateDto.createDto(validCompanyName, businessNumber, validPhoneNumber, validAddress))))
                .andExpect(status().isBadRequest());
    }

}