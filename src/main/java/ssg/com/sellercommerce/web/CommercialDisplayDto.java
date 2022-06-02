package ssg.com.sellercommerce.web;

import lombok.Data;

@Data
public class CommercialDisplayDto {
    private Long commercialId;
    private Long companyId;
    private Long itemId;
    private String itemName;
    private Integer itemPrice;
    private Integer bid;
}
