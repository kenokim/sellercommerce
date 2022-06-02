package ssg.com.sellercommerce.web;

import lombok.Data;

@Data
public class CompanyCreateDto {
    private String name;
    private Long businessNumber;
    private Integer phoneNumber;
    private String address;
}
