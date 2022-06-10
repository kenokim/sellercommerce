package ssg.com.sellercommerce.web;

import lombok.Data;
import ssg.com.sellercommerce.domain.Company;

@Data
public class CompanyDto {
    private Long id;
    private String name;

    public static CompanyDto createByEntity(Company company) {
        CompanyDto dto = new CompanyDto();
        dto.id = company.getId();
        dto.name = company.getCompanyName();
        return dto;
    }
}
