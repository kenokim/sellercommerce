package ssg.com.sellercommerce.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Item {
    @Id @GeneratedValue
    @Column(name = "item_id", length = 10)
    private Long id;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "company_name")
    //private Company company;

    @NotNull
    private String companyName;

    @NotNull
    private String itemName;

    private int price;

    private int stockQuantity;

    public static Item createItem(String companyName, String itemName, int price, int stockQuantity) {
        Item item = new Item();
        item.companyName = companyName;
        item.itemName = itemName;
        item.price = price;
        item.stockQuantity = stockQuantity;
        return item;
    }
}
