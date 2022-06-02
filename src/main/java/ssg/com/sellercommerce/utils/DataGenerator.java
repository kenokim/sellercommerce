package ssg.com.sellercommerce.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ssg.com.sellercommerce.domain.Item;
import ssg.com.sellercommerce.repository.ItemRepository;

@Component
@Transactional
@RequiredArgsConstructor
public class DataGenerator implements CommandLineRunner {

    private final ItemRepository itemRepository;

    @Override
    public void run(String... args) throws Exception {
        generateItems();
    }

    private void generateItems() {
        saveItem("이마트", "허쉬 초코멜로쿠키 45g", 600, 10);
        saveItem("신세계백화점", "크리스피롤 12 곡 180g", 2800, 5);
        saveItem("삼성전자", "갤럭시 22 자급제모델", 1200000, 0);
        saveItem("이마트", "말랑카우 핸드워시 250ml", 2600, 6);
        saveItem("신세계백화점", "삼립 미니꿀호떡 322g", 1200, 4);
        saveItem("이마트", "피코크 어랑손만두 어랑만두 700g", 6400, 2);
        saveItem("신세계백화점", "빼빼로바 아몬드아이스크림 4 입", 2800, 1);
        saveItem("이마트", "피코크 에이 클래스 우유 900ml", 2500, 3);
        saveItem("이마트", "아삭달콤 방울토마토 500g", 4500, 0);
        saveItem("이마트", "[롯데삼강] 돼지바 (70ml*6 입)", 3000, 1);
        saveItem("나이키", "나이키 덩크로우 흰검", 129000, 4);
        saveItem("스타벅스", "이달의 원두 500g", 18500, 4);
        saveItem("스타벅스", "아쿠아 머그", 23000, 7);
        saveItem("삼성전자", "삼성전자 43 인치 스마트모니터", 480000, 2);
        saveItem("나이키", "나이키 헤리티지 스우시 캡", 25000, 5);
        saveItem("이상해씨샵", "이상해씨 인형", 15000, 4);
        saveItem("이상해씨샵", "이상해씨 덩쿨", 5000, 4);
        saveItem("이상해씨샵", "이상해씨 캐릭터 굿즈", 35000, 8);
        saveItem("이상해씨샵", "이상해씨 귀여운 인형", 25000, 3);

    }

    private void saveItem(String companyName, String itemName, int price, int stockQuantity) {
        Item item = Item.createItem(companyName, itemName, price, stockQuantity);
        itemRepository.save(item);
    }
}
