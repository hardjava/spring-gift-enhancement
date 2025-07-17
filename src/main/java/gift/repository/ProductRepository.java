package gift.repository;

import gift.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    default Product findProductByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID의 상품을 찾을 수 없습니다."));
    }

    Optional<Product> findByName(String name);

    @Query(value = """
            SELECT *
            FROM product
            ORDER BY created_at DESC
            LIMIT :limit
            OFFSET :offset
            """, nativeQuery = true)
    List<Product> findAllByPaging(@Param("limit") int limit, @Param("offset") int offset);


    @Query(value = """
            SELECT *
            FROM product
            WHERE image_url LIKE :search
            OR name LIKE :search
            ORDER BY created_at DESC 
            LIMIT :limit
            OFFSET :offset      
            """, nativeQuery = true)
    List<Product> findByKeyword(@Param("limit") int limit, @Param("offset") int offset, @Param("search") String search);
}
