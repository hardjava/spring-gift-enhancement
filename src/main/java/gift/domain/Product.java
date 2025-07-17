package gift.domain;

import gift.validation.ProductNameValidator;
import gift.validation.ProductPriceValidator;
import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private String imageUrl;

    public Product() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Product(String name, Long price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public static Product of(String name, Long price, String imageUrl) {
        ProductNameValidator.validateName(name);
        ProductPriceValidator.validatePrice(price);
        return new Product(name, price, imageUrl);
    }

    public void update(String name, Long price, String imageUrl) {
        ProductNameValidator.validateName(name);
        ProductPriceValidator.validatePrice(price);
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}
