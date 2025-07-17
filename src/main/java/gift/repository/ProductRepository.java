package gift.repository;

import gift.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    default Product findProductByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID의 상품을 찾을 수 없습니다."));
    }

    Optional<Product> findByName(String name);
}
