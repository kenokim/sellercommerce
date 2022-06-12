package ssg.com.sellercommerce.web;

import lombok.Data;
import ssg.com.sellercommerce.domain.Item;

import java.util.ArrayList;
import java.util.List;

@Data
public class ItemListDto {
    private List<ItemDto> items = new ArrayList();

    public static ItemListDto createByEntity(List<Item> items) {
        ItemListDto dto = new ItemListDto();
        items.stream().forEach(item -> {
            dto.items.add(ItemDto.createByEntity(item));
        });
        return dto;
    }
}
