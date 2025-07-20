package gift.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class OptionQuantity {
    @Column(name = "quantity")
    private int quantity;

    protected OptionQuantity() {

    }

    public OptionQuantity(int quantity) {
        validateQuantity(quantity);
        this.quantity = quantity;
    }

    private static void validateQuantity(int quantity) {
        if (quantity < 0 || quantity > 100000000) {
            throw new IllegalArgumentException("quantity는 0 이상, 1억 미만 이어야 합니다.");
        }
    }
}
