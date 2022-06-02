package ssg.com.sellercommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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

    private final Long validBusinessNumber = 1000000000L;
    private final String validCompanyName = "hello";
    private final String validPhoneNumber = "01012345678";
    private final String validAddress = "Hello";


    @ParameterizedTest
    @ValueSource(longs = { 1000000000L, 3000000000L })
    public void CompanyCreate_ValidInput_ReturnsOK(Long businessNumber) throws Exception {
        this.mockMvc.perform(post("/company")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(CompanyCreateDto.of(validCompanyName, businessNumber, validPhoneNumber, validAddress))))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @ValueSource(strings = { "021234567", "01012345678" })
    public void CompanyCreate_ValidInput2_ReturnsOK(String phoneNumber) throws Exception {
        this.mockMvc.perform(post("/company")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(CompanyCreateDto.of(validCompanyName, validBusinessNumber, phoneNumber, validAddress))))
                .andExpect(status().isOk());
    }


    @ParameterizedTest
    @ValueSource(longs = { 10L, 10000000000L })
    public void CompanyCreate_InvalidBusinessNumber_ReturnsBadRequest(Long businessNumber) throws Exception {
        this.mockMvc.perform(post("/company")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(CompanyCreateDto.of(validCompanyName, businessNumber, validPhoneNumber, validAddress))))
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @ValueSource(strings = { "02123456", "010123456789" })
    public void CompanyCreate_InvalidPhoneNumber_ReturnsBadRequest(String phoneNumber) throws Exception {
        this.mockMvc.perform(post("/company")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(CompanyCreateDto.of(validCompanyName, validBusinessNumber, phoneNumber, validAddress))))
                .andExpect(status().isBadRequest());
    }

}