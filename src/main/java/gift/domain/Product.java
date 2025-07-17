package gift.domain;

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

    protected Product() {

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

    public Product(String name, Long price, String imageUrl) {
        validateName(name);
        validatePrice(price);
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public static Product of(String name, Long price, String imageUrl) {
        return new Product(name, price, imageUrl);
    }

    public void update(String name, Long price, String imageUrl) {
        validateName(name);
        validatePrice(price);
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    private static void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("상품 이름은 비어 있을 수 없습니다.");
        }

        if (name.length() > 15) {
            throw new IllegalArgumentException("상품 이름은 공백 포함 최대 15자까지 입력할 수 있습니다.");
        }

        if (!name.matches("^[\\p{L}\\p{N}\\s\\(\\)\\[\\]\\+\\-\\&/_]*$")) {
            throw new IllegalArgumentException("상품 이름에는 ( ), [ ], +, -, &, /, _ 의 특수 문자만 사용할 수 있습니다.");
        }

        if (name.contains("카카오")) {
            throw new IllegalArgumentException("상품 이름에 '카카오'를 포함하려면 MD 승인이 필요합니다.");
        }
    }

    private static void validatePrice(Long price) {
        if (price == null) {
            throw new IllegalArgumentException("가격은 필수 입력입니다.");
        }

        if (price < 0) {
            throw new IllegalArgumentException("가격은 0 이상이어야 합니다.");
        }
    }
}
