package ssg.com.sellercommerce.web;

import lombok.Data;
import ssg.com.sellercommerce.domain.Contract;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ContractDto {
    private Long id;
    private String companyName;

    private LocalDateTime startAt;
    private LocalDateTime endAt;

    public static ContractDto createByEntity(Contract contract) {
        ContractDto dto = new ContractDto();
        dto.id = contract.getId();
        dto.companyName = contract.getCompany().getCompanyName();
        dto.startAt = contract.getStartAt();
        dto.endAt = contract.getEndAt();
        return dto;
    }
}
