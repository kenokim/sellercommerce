package ssg.com.sellercommerce.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssg.com.sellercommerce.web.CommercialCreateDto;

@Slf4j
@RestController
@RequestMapping("/commercial")
@RequiredArgsConstructor
public class CommercialController {

    @PostMapping("{commercialId}") // 광고과금
    public ResponseEntity click(@PathVariable("commercialId") Long commercialId) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping // 광고전시
    public ResponseEntity display() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping // 광고입찰
    public ResponseEntity createBid(CommercialCreateDto commercialCreateDto) {
        return new ResponseEntity(HttpStatus.OK);
    }
}
