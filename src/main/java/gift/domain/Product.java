package gift.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @Column(nullable = false)
    private ProductName name;

    @Embedded
    @Column(nullable = false)
    private ProductPrice price;

    @Column(nullable = false)
    private String imageUrl;

    protected Product() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name.getName();
    }

    public Long getPrice() {
        return price.getPrice();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Product(String name, Long price, String imageUrl) {
        this.name = new ProductName(name);
        this.price = new ProductPrice(price);
        this.imageUrl = imageUrl;
    }

    public static Product of(String name, Long price, String imageUrl) {
        return new Product(name, price, imageUrl);
    }

    public void update(String name, Long price, String imageUrl) {
        this.name = new ProductName(name);
        this.price = new ProductPrice(price);
        this.imageUrl = imageUrl;
    }
}
