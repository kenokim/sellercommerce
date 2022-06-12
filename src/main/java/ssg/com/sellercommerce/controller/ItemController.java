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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssg.com.sellercommerce.domain.Item;
import ssg.com.sellercommerce.service.ItemService;
import ssg.com.sellercommerce.web.CompanyDto;
import ssg.com.sellercommerce.web.ItemListDto;

import java.util.List;

@Tag(name = "상품 DOMAIN - help")
@Slf4j
@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    /**
     * 상품 목록을 조회하는 편의 메소드 입니다.
     */

    @Operation(summary = "상품조회 - help")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ItemListDto.class)))
    @GetMapping
    public ResponseEntity select() {
        List<Item> items = itemService.findAll();
        ItemListDto dto = ItemListDto.createByEntity(items);
        return new ResponseEntity(dto, HttpStatus.OK);
    }
}
