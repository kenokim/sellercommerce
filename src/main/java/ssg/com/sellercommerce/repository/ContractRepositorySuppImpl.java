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

    /**
     * curr 시점에 유효한 계약을 모두 조회합니다.
     * 로직 오류가 없을 경우 하나 또는 빈 리스트를 리턴합니다.
     */
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
