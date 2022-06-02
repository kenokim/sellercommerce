package ssg.com.sellercommerce.domain;

import com.sun.istack.NotNull;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Getter
@SequenceGenerator(
        name = "DECIMAL_SEQ_GENERATOR",
        sequenceName = "DECIMAL_SEQ",
        initialValue = 1000000000
)
public class Contract {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DECIMAL_SEQ_GENERATOR")
    @Column(name = "contract_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @NotNull
    private LocalDateTime startAt;

    @NotNull
    private LocalDateTime endAt;

    protected Contract() {}

    public static Contract create(Company company, LocalDateTime startAt, LocalDateTime endAt) {
        Contract contract = new Contract();
        contract.company = company;
        contract.startAt = startAt;
        contract.endAt = endAt;
        return contract;
    }
}
