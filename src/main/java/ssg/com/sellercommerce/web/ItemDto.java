package ssg.com.sellercommerce.web;

import lombok.Data;
import ssg.com.sellercommerce.domain.Item;

@Data
public class ItemDto {
    private Long id;
    private String companyName;
    private String itemName;
    private Integer price;
    private Integer stockQuantity;

    public static ItemDto createByEntity(Item item) {
        ItemDto dto = new ItemDto();
        dto.id = item.getId();
        dto.companyName = item.getCompanyName();
        dto.itemName = item.getItemName();
        dto.price = item.getPrice();
        dto.stockQuantity = item.getStockQuantity();
        return dto;
    }
}
