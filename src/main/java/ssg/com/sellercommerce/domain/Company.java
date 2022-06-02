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
public class Company {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DECIMAL_SEQ_GENERATOR")
    @Column(name = "company_id", length = 10)
    private Long id;

    @NotNull
    private String companyName;

    @NotNull @Column(length = 10)
    private Long businessNumber;

    private Long phoneNumber;

    private String address;

    protected Company() {}

    public static Company create(String companyName, Long businessNumber, Long phoneNumber, String address) {
        Company company = new Company();
        company.companyName = companyName;
        company.businessNumber = businessNumber;
        company.phoneNumber = phoneNumber;
        company.address = address;
        return company;
    }
}
