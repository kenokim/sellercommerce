package ssg.com.sellercommerce.web;

import lombok.Data;
import ssg.com.sellercommerce.domain.Company;

@Data
public class CompanyDto {
    private Long id;
    private String name;
    private Long businessNumber;
    private String address;
    private String phoneNumber;

    public static CompanyDto createByEntity(Company company) {
        CompanyDto dto = new CompanyDto();
        dto.id = company.getId();
        dto.name = company.getCompanyName();
        dto.businessNumber = company.getBusinessNumber();
        dto.phoneNumber = '0'+company.getPhoneNumber().toString();
        dto.address = company.getAddress();
        return dto;
    }
}
