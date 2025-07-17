package gift.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "wish")
public class Wish extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public Long getId() {
        return id;
    }

    protected Wish() {

    }

    public Wish(Member member, Product product) {
        this.member = member;
        this.product = product;
    }
}
