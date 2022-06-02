package ssg.com.sellercommerce.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssg.com.sellercommerce.domain.Item;
import ssg.com.sellercommerce.exception.IllegalRequestException;
import ssg.com.sellercommerce.repository.ItemRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;

    public Item findByCompanyName(String companyName) {
        return itemRepository.findByCompanyName(companyName).orElseThrow(() -> new IllegalRequestException("존재하지 않는 업체입니다."));
    }

    // 상품목록에 존재하는 업체인지
    public Boolean isValidCompanyName(String companyName) {
        return itemRepository.countAllByCompanyName(companyName) != 0;
    }
}
