package ssg.com.sellercommerce.domain;

import com.sun.istack.NotNull;
import lombok.Getter;

import javax.persistence.*;

@Entity @Getter
@SequenceGenerator(
        name = "DECIMAL_SEQ_GENERATOR",
        sequenceName = "DECIMAL_SEQ",
        initialValue = 1000000000
)
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DECIMAL_SEQ_GENERATOR")
    @Column(name = "item_id", length = 10)
    private Long id;

    @NotNull
    private String companyName;

    @NotNull
    private String itemName;

    private int price;

    private int stockQuantity;

    protected Item() {}

    public static Item createItem(String companyName, String itemName, int price, int stockQuantity) {
        Item item = new Item();
        item.companyName = companyName;
        item.itemName = itemName;
        item.price = price;
        item.stockQuantity = stockQuantity;
        return item;
    }
}
