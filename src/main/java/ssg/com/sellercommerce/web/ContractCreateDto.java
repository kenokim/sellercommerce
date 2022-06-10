package ssg.com.sellercommerce.web;

import lombok.Data;
import ssg.com.sellercommerce.utils.validator.DecimalConstraint;

@Data
public class ContractCreateDto {

    @DecimalConstraint
    private Long companyId;
}
