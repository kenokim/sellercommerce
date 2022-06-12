package ssg.com.sellercommerce.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssg.com.sellercommerce.domain.Contract;
import ssg.com.sellercommerce.domain.Settlement;
import ssg.com.sellercommerce.service.SettlementService;
import ssg.com.sellercommerce.web.CompanyDto;
import ssg.com.sellercommerce.web.ContractListDto;
import ssg.com.sellercommerce.web.SettlementListDto;

import java.util.List;

@Tag(name = "정산 DOMAIN")
@Slf4j
@RestController
@RequestMapping("/billing")
@RequiredArgsConstructor
public class SettlementController {
    private final SettlementService settlementService;

    /**
     * 정산 집계 데이터를 조회할 수 있는 편의 메소드입니다.
     */
    @Operation(summary = "정산조회 - help")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SettlementListDto.class)))
    @GetMapping
    public ResponseEntity select() {
        List<Settlement> settlements = settlementService.findAll();
        SettlementListDto dto = SettlementListDto.createByEntity(settlements);
        return new ResponseEntity(dto, HttpStatus.OK);
    }
}
