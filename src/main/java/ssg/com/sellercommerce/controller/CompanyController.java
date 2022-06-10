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
import org.w3c.dom.stylesheets.LinkStyle;
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
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @Operation(summary = "업체생성")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CompanyDto.class)))
    @PostMapping // 업체생성
    public ResponseEntity register(@Valid @RequestBody CompanyCreateDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalRequestException(bindingResult.getFieldError().getDefaultMessage());
        }
        Long companyId = companyService.register(dto.getName(), dto.getBusinessNumber(), dto.getLongPhoneNumber(), dto.getAddress());
        return new ResponseEntity(companyId, HttpStatus.OK);
    }

    @Operation(summary = "업체조회 help")
    @GetMapping
    public ResponseEntity select() {
        List<Company> companies = companyService.findAll();
        CompanyListDto dto = CompanyListDto.createByEntity(companies);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

}
