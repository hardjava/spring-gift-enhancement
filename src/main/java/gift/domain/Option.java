package gift.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "option",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_productId_name", columnNames = {"product_id", "name"})
        }
)
public class Option extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Embedded
    private OptionName name;

    @Embedded
    private OptionQuantity quantity;

    protected Option() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name.getName();
    }

    public int getQuantity() {
        return quantity.getQuantity();
    }

    public Option(Product product, String name, int quantity) {
        this.product = product;
        this.name = new OptionName(name);
        this.quantity = new OptionQuantity(quantity);
    }
}
