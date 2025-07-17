package gift.repository;

import gift.domain.Wish;
import gift.domain.WishSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WishListRepository extends JpaRepository<Wish, Long> {
    @Query(value = """
            select p.name as productName, count(*) as count
            from wish w
            join product p on w.product_id = p.id
            where w.member_id = :memberId
            group by p.name, w.product_id;
            """, nativeQuery = true)
    List<WishSummary> findAllWishSummaryByMemberId(@Param("memberId") Long memberId);

    boolean existsWishByMember_IdAndProduct_Id(Long memberId, Long productId);

    void deleteWishByMember_IdAndProduct_Id(Long memberId, Long productId);
}
