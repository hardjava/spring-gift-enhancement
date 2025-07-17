package gift.domain;

public class WishSummary {
    private String productName;
    private Long count;

    public WishSummary(String productName, Long count) {
        this.productName = productName;
        this.count = count;
    }

    public String getProductName() {
        return productName;
    }

    public Long getCount() {
        return count;
    }
}
