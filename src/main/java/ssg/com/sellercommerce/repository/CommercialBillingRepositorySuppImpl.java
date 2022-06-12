package ssg.com.sellercommerce.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import ssg.com.sellercommerce.domain.QCommercial;
import ssg.com.sellercommerce.domain.QCommercialBilling;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class CommercialBillingRepositorySuppImpl implements CommercialBillingRepositorySupp {
    private final JPAQueryFactory query;


    /**
     * from 과 to 기간에 속하는 광고과금을 집계합니다.
     */
    @Override
    public Tuple aggregateSettlements(Long commercialId, LocalDateTime from, LocalDateTime to) {
        QCommercialBilling commercialBilling = QCommercialBilling.commercialBilling;
        QCommercial commercial = QCommercial.commercial;
        return query.select(commercialBilling.count(), commercialBilling.bid.sum())
                .from(commercialBilling)
                .where(commercialBilling.clickedAt.goe(from))
                .where(commercialBilling.clickedAt.loe(to))
                .where(commercialBilling.commercial.id.eq(commercialId))
                .fetchOne();
        /*JPAQuery<Tuple> q = query.select(commercialBilling.count(), commercialBilling.bid.sum())
                .from(commercialBilling)
                ;
        q.where(commercialBilling.commercial.id.eq(commercialId))
                .where(commercialBilling.clickedAt.before(to))
                .where(commercialBilling.clickedAt.after(from))
        ;
        return q.fetchOne();*/
    }
}
