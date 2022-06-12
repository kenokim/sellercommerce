package ssg.com.sellercommerce.web;

import lombok.Data;
import ssg.com.sellercommerce.domain.Settlement;

import java.util.ArrayList;
import java.util.List;

@Data
public class SettlementListDto {
    private List<SettlementDto> settlements = new ArrayList<>();

    public static SettlementListDto createByEntity(List<Settlement> settlements) {
        SettlementListDto dto = new SettlementListDto();
        settlements.stream().forEach(s -> {
            dto.settlements.add(SettlementDto.createByEntity(s));
        });
        return dto;
    }
}
