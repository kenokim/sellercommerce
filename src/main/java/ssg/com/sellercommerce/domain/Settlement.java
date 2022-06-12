package ssg.com.sellercommerce.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@SequenceGenerator(
        name = "DECIMAL_SEQ_GENERATOR",
        sequenceName = "DECIMAL_SEQ",
        initialValue = 1000000000
)
public class Settlement {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DECIMAL_SEQ_GENERATOR")
    @Column(name = "settlement_id", length = 10)
    private Long id;

    // 클릭일자
    private LocalDateTime clickDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    private String companyName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commercial_id")
    private Commercial commercial;

    private String itemName;

    private Long clickCount;

    private Integer totalBilling;

    protected Settlement() {}

    @Builder
    public static Settlement create(Company company, Item item, Commercial commercial, LocalDateTime clickDate, Long clickCount, Integer totalBilling) {
        Settlement settlement = new Settlement();
        settlement.company = company;
        settlement.commercial = commercial;
        settlement.clickCount = clickCount;
        settlement.companyName = company.getCompanyName();
        settlement.itemName = item.getItemName();
        settlement.clickDate = clickDate;
        settlement.totalBilling = totalBilling;
        return settlement;
    }
}
