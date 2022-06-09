package ssg.com.sellercommerce.domain;

import com.sun.istack.NotNull;
import lombok.Getter;

import javax.persistence.*;

@Entity @Getter
@SequenceGenerator(
        name = "DECIMAL_SEQ_GENERATOR",
        sequenceName = "DECIMAL_SEQ",
        initialValue = 1000000000
)
public class Commercial {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DECIMAL_SEQ_GENERATOR")
    @Column(name = "commercial_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @NotNull
    private Integer bid;

    protected Commercial() {}
    public static Commercial create(Company company, Item item, Integer bid) {
        Commercial commercial = new Commercial();
        commercial.company = company;
        commercial.item = item;
        commercial.bid = bid;
        return commercial;
    }
}
