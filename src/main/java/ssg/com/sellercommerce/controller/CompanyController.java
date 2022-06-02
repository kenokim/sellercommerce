package ssg.com.sellercommerce.controller;

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

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    @PostMapping // 업체생성
    public ResponseEntity register(@Valid @RequestBody CompanyCreateDto companyCreateDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalRequestException(bindingResult.getFieldError().getDefaultMessage());
        }
        return new ResponseEntity("Successfully created", HttpStatus.OK);
    }
}
