package ssg.com.sellercommerce.web;

import lombok.Data;
import ssg.com.sellercommerce.domain.Contract;

import java.util.ArrayList;
import java.util.List;

@Data
public class ContractListDto {
    private List<ContractDto> contracts = new ArrayList<>();

    public static ContractListDto createByEntity(List<Contract> contracts) {
        ContractListDto dto = new ContractListDto();
        contracts.stream().forEach(contract -> {
            dto.contracts.add(ContractDto.createByEntity(contract));
        });
        return dto;
    }
}
