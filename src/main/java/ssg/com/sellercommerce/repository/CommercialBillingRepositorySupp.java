package ssg.com.sellercommerce.repository;

import com.querydsl.core.Tuple;

import java.time.LocalDateTime;

public interface CommercialBillingRepositorySupp {
    Tuple aggregateSettlements(Long commercialId, LocalDateTime from, LocalDateTime to);
}
