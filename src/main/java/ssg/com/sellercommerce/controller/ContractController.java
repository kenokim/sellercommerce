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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssg.com.sellercommerce.domain.Contract;
import ssg.com.sellercommerce.service.ContractService;
import ssg.com.sellercommerce.web.CompanyDto;
import ssg.com.sellercommerce.web.ContractCreateDto;
import ssg.com.sellercommerce.web.ContractListDto;

import java.util.List;

@Tag(name = "계약 DOMAIN")
@ApiResponse(responseCode = "400", description = "잘못된 요청입니다. 응답된 로그를 보고 올바른 요청을 보내주세요.",
        content = @Content(mediaType = "application/json"))
@Slf4j
@RestController
@RequestMapping("/contracts")
@RequiredArgsConstructor
public class ContractController {
    private final ContractService contractService;

    /**
     * 계약생성 API
     * 등록된 업체에 한해서 계약을 생성할 수 있습니다. 요청 시점에 유효한 계약이 있을 경우, 중복 계약으로 판단되어 예외를 응답합니다.
     */
    @Operation(summary = "계약생성")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Long.class)))
    @PostMapping
    public ResponseEntity createContract(ContractCreateDto dto) {
        Long contractId = contractService.createContract(dto.getCompanyId());
        return new ResponseEntity(contractId, HttpStatus.OK);
    }

    /**
     * 계약 목록을 조회하는 편의 메소드입니다.
     */
    @Operation(summary = "계약조회 - help")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ContractListDto.class)))
    @GetMapping
    public ResponseEntity select() {
        List<Contract> contracts = contractService.findAll();
        ContractListDto dto = ContractListDto.createByEntity(contracts);
        return new ResponseEntity(dto, HttpStatus.OK);
    }
}
