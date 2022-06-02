package ssg.com.sellercommerce.web;

import lombok.Data;
import ssg.com.sellercommerce.domain.Commercial;

import java.util.ArrayList;
import java.util.List;

@Data
public class CommercialDisplayDto {
    private List<CommercialDto> commercialDtoList = new ArrayList<>();

    public static CommercialDisplayDto createByEntity(Commercial commercial) {
        return new CommercialDisplayDto();
    }
}

class CommercialDto {
    private Long commercialId;
    private Long companyId;
    private Long itemId;
    private String itemName;
    private Integer itemPrice;
    private Integer bid;
}
