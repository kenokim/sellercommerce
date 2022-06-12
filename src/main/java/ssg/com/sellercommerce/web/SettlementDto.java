package ssg.com.sellercommerce.web;

import lombok.Data;
import ssg.com.sellercommerce.domain.Settlement;

import java.time.LocalDateTime;

@Data
public class SettlementDto {
    private Long id;
    private Long clickCount;

    private String companyName;
    private String itemName;
    private Integer billing;
    private LocalDateTime clickAt;


    public static SettlementDto createByEntity(Settlement settlement) {
        SettlementDto dto = new SettlementDto();
        dto.id = settlement.getId();
        dto.clickCount = settlement.getClickCount();
        dto.companyName = settlement.getCompanyName();
        dto.itemName = settlement.getItemName();
        dto.billing = settlement.getTotalBilling();
        dto.clickAt = settlement.getClickDate();
        return dto;
    }
}
