package ssg.com.sellercommerce.repository;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import ssg.com.sellercommerce.domain.Commercial;
import ssg.com.sellercommerce.domain.QCommercial;
import ssg.com.sellercommerce.domain.QCompany;
import ssg.com.sellercommerce.domain.QItem;
import ssg.com.sellercommerce.repository.CommercialRepositorySupp;

import java.util.List;

@RequiredArgsConstructor
public class CommercialRepositorySuppImpl implements CommercialRepositorySupp {

    private final JPAQueryFactory query;

    /**
     * 상위 3개 입찰가의 재고가 존재하는 광고를 조회합니다.
     */
    @Override
    public List<Commercial> displayCommercials(int number) {
        QCommercial commercial = QCommercial.commercial;
        QCompany company = QCompany.company;
        QItem item = QItem.item;
        JPQLQuery<Commercial> q = query.selectFrom(commercial)
                //.leftJoin(company)
                //.fetchJoin()
                //.leftJoin(item)
                //.fetchJoin()
        ;
        q.where(commercial.item.stockQuantity.gt(0));
        q.orderBy(commercial.bid.desc());
        q.limit(3L);
        //q.orderBy(commercial.bid.desc());
        //q.limit(3);

        return q.fetch();
    }
}
