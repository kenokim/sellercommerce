package ssg.com.sellercommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ssg.com.sellercommerce.web.CommercialCreateDto;
import ssg.com.sellercommerce.web.CompanyCreateDto;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class CommercialControllerTest {

    @Autowired private CommercialController commercialController;
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    private final Long validCompanyId = 1000000000L;

    private final Long validItemId = 1000000000L;

    @ParameterizedTest
    @ValueSource(ints = { 100, 1000000, 0 })
    public void CommercialCreate_ValidInput_ReturnsOK(Integer bid) throws Exception {
        this.mockMvc.perform(post("/commercial")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(CommercialCreateDto.of(validCompanyId, validItemId, bid))))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @ValueSource(ints = { -100, 1000001, 100000000 })
    public void CommercialCreate_InValidBid_ReturnsBadRequest(Integer bid) throws Exception {
        this.mockMvc.perform(post("/commercial")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(CommercialCreateDto.of(validCompanyId, validItemId, bid))))
                .andExpect(status().isBadRequest());
    }

}
