package ssg.com.sellercommerce.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
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
    private Integer price;
}
