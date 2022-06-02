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
public class CommercialBilling {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DECIMAL_SEQ_GENERATOR")
    @Column(name = "billing_id", length = 10)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commercial_id")
    private Commercial commercial;

    private LocalDateTime clickedAt;

    @NotNull
    private Integer bid;

    protected CommercialBilling() {}

    public static CommercialBilling create(Commercial commercial, LocalDateTime clickedAt, Integer bid) {
        CommercialBilling billing = new CommercialBilling();
        billing.commercial = commercial;
        billing.bid = bid;
        billing.clickedAt = clickedAt;
        return billing;
    }
}
