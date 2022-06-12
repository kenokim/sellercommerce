package ssg.com.sellercommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ssg.com.sellercommerce.service.CommercialService;
import ssg.com.sellercommerce.web.CommercialCreateDto;
import ssg.com.sellercommerce.web.CompanyCreateDto;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class CommercialControllerTest {

    @Autowired private CommercialController commercialController;
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @MockBean private CommercialService commercialService;

    private final Long companyId = 1000000000L;

    private final Long itemId = 1000000000L;

    private final Integer bid = 1000000;
    private final Long commercialId = 100000000L;

    @BeforeEach
    public void setup() {
        when(commercialService.createCommercial(companyId, itemId, bid))
                .thenReturn(commercialId);
    }

    @ParameterizedTest
    @ValueSource(ints = { 100, 1000000, 0 })
    public void CommercialCreate_ValidInput_ReturnsOK(Integer bid) throws Exception {
        this.mockMvc.perform(post("/commercials")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(CommercialCreateDto.of(companyId, itemId, bid))))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @ValueSource(ints = { -100, 1000001, 100000000 })
    public void CommercialCreate_InValidBid_ReturnsBadRequest(Integer bid) throws Exception {
        this.mockMvc.perform(post("/commercials")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(CommercialCreateDto.of(companyId, itemId, bid))))
                .andExpect(status().isBadRequest());
    }

}
