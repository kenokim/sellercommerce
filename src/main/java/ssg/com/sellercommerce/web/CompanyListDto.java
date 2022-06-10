package ssg.com.sellercommerce.web;

import lombok.Data;
import ssg.com.sellercommerce.domain.Company;

import java.util.ArrayList;
import java.util.List;

@Data
public class CompanyListDto {
    private List<CompanyDto> companies = new ArrayList<>();

    public static CompanyListDto createByEntity(List<Company> companies) {
        CompanyListDto dto = new CompanyListDto();
        companies.stream().forEach(c -> {
            dto.companies.add(CompanyDto.createByEntity(c));
        });
        return dto;
    }
}
