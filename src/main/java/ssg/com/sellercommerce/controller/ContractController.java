package ssg.com.sellercommerce.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssg.com.sellercommerce.web.ContractCreateDto;

@Slf4j
@RestController
@RequestMapping("/contract")
@RequiredArgsConstructor
public class ContractController {
    @PostMapping // 계약생성
    public ResponseEntity createContract(ContractCreateDto contractCreateDto) {
        return new ResponseEntity(HttpStatus.OK);
    }
}
