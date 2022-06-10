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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssg.com.sellercommerce.exception.IllegalRequestException;
import ssg.com.sellercommerce.web.CompanyCreateDto;
import ssg.com.sellercommerce.web.CompanyDto;

import javax.validation.Valid;

@Tag(name = "업체 DOMAIN")
@Slf4j
@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    @Operation(summary = "업체생성")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CompanyDto.class)))
    @PostMapping // 업체생성
    public ResponseEntity register(@Valid @RequestBody CompanyCreateDto companyCreateDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalRequestException(bindingResult.getFieldError().getDefaultMessage());
        }
        return new ResponseEntity("Successfully created", HttpStatus.OK);
    }


}
