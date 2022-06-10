package ssg.com.sellercommerce.repository;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import ssg.com.sellercommerce.domain.Contract;
import ssg.com.sellercommerce.domain.QContract;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class ContractRepositorySuppImpl implements ContractRepositorySupp {

    private final JPAQueryFactory query;

    @Override
    public List<Contract> findAllByCompanyIdAndCurrentTime(Long companyId, LocalDateTime curr) {
        QContract contract = QContract.contract;
        JPQLQuery<Contract> q = query.selectFrom(contract);
        q.where(contract.company.id.eq(companyId));
        q.where(contract.startAt.before(curr));
        q.where(contract.endAt.after(curr));
        return q.fetch();
    }
}
