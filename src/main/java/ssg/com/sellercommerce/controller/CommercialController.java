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
import ssg.com.sellercommerce.domain.Commercial;
import ssg.com.sellercommerce.exception.IllegalRequestException;
import ssg.com.sellercommerce.service.CommercialService;
import ssg.com.sellercommerce.web.CommercialCreateDto;
import ssg.com.sellercommerce.web.CommercialDisplayDto;

import javax.validation.Valid;
import java.util.List;

/**
 * 광고 DOMAIN 컨트롤러
 */
@Tag(name = "광고 DOMAIN")
@ApiResponse(responseCode = "400", description = "잘못된 요청입니다. 응답된 로그를 보고 올바른 요청을 보내주세요.",
        content = @Content(mediaType = "application/json"))
@Slf4j
@RestController
@RequestMapping("/commercials")
@RequiredArgsConstructor
public class CommercialController {

    private final CommercialService commercialService;

    /**
     * 광고과금 API
     * CPC 방식 과금 - Get 으로 이 API 를 통해 광고를 조회하는 상황을 가정하여, 클릭 시 광고과금 (CommercialBilling) 이 생성됩니다.
     */
    @Operation(summary = "광고과금")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Long.class)))
    @GetMapping("{commercialId}")
    public ResponseEntity click(@PathVariable("commercialId") Long commercialId) {
        Long billingId = commercialService.clickBilling(commercialId);
        return new ResponseEntity(billingId, HttpStatus.OK);
    }

    /**
     * 광고전시 API
     * 광고입찰가 (bid) 최대 상위 3개의 재고가 남은 상품을 전시합니다.
     */
    @Operation(summary = "광고전시")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommercialDisplayDto.class)))
    @GetMapping
    public ResponseEntity<CommercialDisplayDto> display() {
        List<Commercial> commercials = commercialService.displayCommercials(3);
        return new ResponseEntity(CommercialDisplayDto.createByEntity(commercials), HttpStatus.OK);
    }

    /**
     * 광고입찰 API
     * 광고 (Commercial) 을 생성합니다.
     * (컨트롤러에서) ID 가 10자리 인지, 입찰가가 1000000 원 이하인지 검증합니다.
     * (서비스에서) 입력된 상품이 업체가 등록한 상품인지 검증합니다.
     */
    @Operation(summary = "광고입찰")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Long.class)))
    @PostMapping
    public ResponseEntity createCommercial(@Valid @RequestBody CommercialCreateDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalRequestException(bindingResult.getFieldError().getDefaultMessage());
        }
        Long commercialId = commercialService.createCommercial(dto.getCompanyId(), dto.getItemId(), dto.getBid());
        return new ResponseEntity(commercialId, HttpStatus.OK);
    }
}
