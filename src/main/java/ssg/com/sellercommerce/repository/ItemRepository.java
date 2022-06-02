package ssg.com.sellercommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssg.com.sellercommerce.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
