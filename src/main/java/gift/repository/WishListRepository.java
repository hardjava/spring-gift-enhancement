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
            group by p.name, w.product_id
            order by count desc
            limit :limit
            offset :offset
            
            """, nativeQuery = true)
    List<WishSummary> findAllWishSummaryByMemberId(@Param("memberId") Long memberId, @Param("limit") int limit, @Param("offset") int offset);

    @Query(value = """
            select p.name as productName, count(*) as count
            from wish w
            join product p on w.product_id = p.id
            where w.member_id = :memberId
            and p.name like :search
            group by p.name, w.product_id
            order by count desc
            limit :limit
            offset :offset
            """, nativeQuery = true)
    List<WishSummary> findByKeyword(@Param("memberId") Long memberId, @Param("limit") int limit, @Param("offset") int offset, @Param("search") String search);

    boolean existsWishByMember_IdAndProduct_Id(Long memberId, Long productId);

    void deleteWishByMember_IdAndProduct_Id(Long memberId, Long productId);

    @Query(value = """
            select count(*)
            from(
                select count(*)
                from wish w
                join product p on w.product_id = p.id
                where w.member_id = :memberId
                group by p.name, w.product_id
            )
            """, nativeQuery = true)
    long countAll(@Param("memberId") Long memberId);

    @Query(value = """
            select count(*)
            from(
                select count(*)
                from wish w
                join product p on w.product_id = p.id
                where w.member_id = :memberId
                and p.name like :search
                group by p.name, w.product_id
            )
            """, nativeQuery = true)
    long countAllByKeyword(@Param("memberId") Long memberId, @Param("search") String search);
}
