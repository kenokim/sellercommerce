package ssg.com.sellercommerce.web;

import lombok.Data;
import ssg.com.sellercommerce.domain.Commercial;

import java.util.ArrayList;
import java.util.List;

@Data
public class CommercialDisplayDto {
    private List<CommercialDto> commercialDtoList = new ArrayList<>();

    public static CommercialDisplayDto createByEntity(List<Commercial> commercials) {
        CommercialDisplayDto dto = new CommercialDisplayDto();
        commercials.stream().forEach(c -> {
            dto.commercialDtoList.add(CommercialDto.createByEntity(c));
        });
        return dto;
    }
}

@Data
class CommercialDto {
    private Long commercialId;
    private Long companyId;
    private Long itemId;
    private String itemName;
    private Integer itemPrice;
    private Integer bid;

    public static CommercialDto createByEntity(Commercial commercial) {
        CommercialDto dto = new CommercialDto();
        dto.commercialId = commercial.getId();
        dto.companyId = commercial.getCompany().getId();
        dto.bid = commercial.getBid();
        dto.itemName = commercial.getItem().getItemName();
        dto.itemId = commercial.getItem().getId();
        dto.itemPrice = commercial.getItem().getPrice();
        return dto;
    }
}
