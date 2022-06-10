package ssg.com.sellercommerce.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssg.com.sellercommerce.web.ContractCreateDto;

@Tag(name = "계약 DOMAIN")
@Slf4j
@RestController
@RequestMapping("/contract")
@RequiredArgsConstructor
public class ContractController {
    @Operation(summary = "계약생성")
    @PostMapping // 계약생성
    public ResponseEntity createContract(ContractCreateDto contractCreateDto) {
        return new ResponseEntity(HttpStatus.OK);
    }
}
