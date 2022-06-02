package ssg.com.sellercommerce.web;

import lombok.Data;

@Data
public class CompanyCreateDto {
    private String name;
    private Long businessNumber;
    private String phoneNumber;
    private String address;

    private CompanyCreateDto() {

    }

    public static CompanyCreateDto createDto(String name, Long businessNumber, String phoneNumber, String address) {
        CompanyCreateDto dto = new CompanyCreateDto();
        dto.name = name;
        dto.businessNumber = businessNumber;
        dto.phoneNumber = phoneNumber;
        dto.address = address;
        return dto;
    }
}
