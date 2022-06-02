package ssg.com.sellercommerce.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Company {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DECIMAL_SEQ_GENERATOR")
    @Column(name = "company_id", length = 10)
    private Long id;

    @NotNull
    private String companyName;

    @NotNull @Column(length = 10)
    private Integer businessNumber;

    private String phoneNumber;

    private String address;
}
