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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ssg.com.sellercommerce.domain.Company;
import ssg.com.sellercommerce.exception.IllegalRequestException;
import ssg.com.sellercommerce.service.CompanyService;
import ssg.com.sellercommerce.web.CompanyCreateDto;
import ssg.com.sellercommerce.web.CompanyDto;
import ssg.com.sellercommerce.web.CompanyListDto;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "업체 DOMAIN")
@Slf4j
@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    /**
     * 업체생성 API
     * 상품에 등록된 업체에 한해, 업체를 생성합니다.
     */
    @Operation(summary = "업체생성")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CompanyDto.class)))
    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다. 응답된 로그를 보고 올바른 요청을 보내주세요.",
            content = @Content(mediaType = "application/json"))
    @PostMapping // 업체생성
    public ResponseEntity register(@Valid @RequestBody CompanyCreateDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalRequestException(bindingResult.getFieldError().getDefaultMessage());
        }
        Company company = companyService.registerAndReturnEntity(dto.getName(), dto.getBusinessNumber(), dto.extractLongPhoneNumber(), dto.getAddress());
        CompanyDto respDto = CompanyDto.createByEntity(company);
        return new ResponseEntity(respDto, HttpStatus.OK);
    }

    /**
     * 업체 업데이트 API
     * 이미 등록된 업체에 한해, 업체명을 제외하여 업데이트 할 수 있습니다.
     */
    @Operation(summary = "업체 업데이트")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CompanyDto.class)))
    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다. 응답된 로그를 보고 올바른 요청을 보내주세요.",
            content = @Content(mediaType = "application/json"))
    @PostMapping("{companyId}") // 업체 업데이트
    public ResponseEntity update(@PathVariable("companyId") Long companyId, @Valid @RequestBody CompanyCreateDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalRequestException(bindingResult.getFieldError().getDefaultMessage());
        }
        Company company = companyService.update(companyId, dto.getName(), dto.getBusinessNumber(), dto.extractLongPhoneNumber(), dto.getAddress());
        CompanyDto respDto = CompanyDto.createByEntity(company);
        return new ResponseEntity(respDto, HttpStatus.OK);
    }

    /**
     * 업체 목록을 조회하는 편의 메소드입니다.
     */
    @Operation(summary = "업체조회 help")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CompanyListDto.class)))
    @GetMapping
    public ResponseEntity select() {
        List<Company> companies = companyService.findAll();
        CompanyListDto dto = CompanyListDto.createByEntity(companies);
        return new ResponseEntity(dto, HttpStatus.OK);
    }
}
