package ssg.com.sellercommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssg.com.sellercommerce.domain.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByCompanyName(String companyName);

    Integer countAllByCompanyName(String companyName);

    List<Item> findAllByCompanyName(String companyName);

    Optional<Item> findByItemId(Long itemId);
}
