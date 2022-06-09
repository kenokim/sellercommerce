package ssg.com.sellercommerce.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ssg.com.sellercommerce.domain.Commercial;
import ssg.com.sellercommerce.exception.IllegalRequestException;
import ssg.com.sellercommerce.service.CommercialService;
import ssg.com.sellercommerce.web.CommercialCreateDto;
import ssg.com.sellercommerce.web.CommercialDisplayDto;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/commercial")
@RequiredArgsConstructor
public class CommercialController {

    private final CommercialService commercialService;

    @PostMapping("{commercialId}") // 광고과금
    public ResponseEntity click(@PathVariable("commercialId") Long commercialId) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping // 광고전시
    public ResponseEntity<CommercialDisplayDto> display() {
        List<Commercial> commercials = commercialService.displayCommercials(3);
        return new ResponseEntity(CommercialDisplayDto.createByEntity(null), HttpStatus.OK);
    }

    @PostMapping // 광고입찰
    public ResponseEntity createCommercial(@Valid @RequestBody CommercialCreateDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalRequestException(bindingResult.getFieldError().getDefaultMessage());
        }
        Long commercialId = commercialService.createCommercial(dto.getCompanyId(), dto.getItemId(), dto.getBid());
        return new ResponseEntity(commercialId, HttpStatus.OK);
    }
}
