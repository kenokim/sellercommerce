package ssg.com.sellercommerce.web;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import ssg.com.sellercommerce.utils.validator.DecimalConstraint;


@Data
public class CommercialCreateDto {
    @DecimalConstraint
    private Long companyId;
    @DecimalConstraint
    private Long itemId;
    @Range(min = 0, max = 1000000)
    private Integer bid;


    private CommercialCreateDto() {}

    public static CommercialCreateDto of(Long companyId, Long itemId, Integer bid) {
        CommercialCreateDto dto = new CommercialCreateDto();
        dto.companyId = companyId;
        dto.itemId = itemId;
        dto.bid = bid;
        return dto;
    }
}
