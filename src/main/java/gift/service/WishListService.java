package gift.service;

import gift.domain.Member;
import gift.domain.Product;
import gift.domain.Wish;
import gift.dto.WishSummaryResponseDto;
import gift.repository.ProductRepository;
import gift.repository.WishListRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class WishListService {
    private final WishListRepository wishListRepository;
    private final ProductRepository productRepository;

    public WishListService(WishListRepository wishListRepository, ProductRepository productRepository) {
        this.wishListRepository = wishListRepository;
        this.productRepository = productRepository;
    }

    public List<WishSummaryResponseDto> findAllWishSummaryByMemberId(Long memberId) {

        return wishListRepository.findAllWishSummaryByMemberId(memberId)
                .stream()
                .map(WishSummaryResponseDto::from)
                .toList();
    }

    @Transactional
    public void saveWish(Member member, Long productId) {
        Product findProduct = productRepository.findProductByIdOrElseThrow(productId);
        Wish wish = new Wish(member, findProduct);

        wishListRepository.save(wish);
    }

    @Transactional
    public void deleteWish(Long memberId, Long productId) {
        if (!wishListRepository.existsWishByMember_IdAndProduct_Id(memberId, productId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 상품을 찾을 수 없습니다.");
        }

        wishListRepository.deleteWishByMember_IdAndProduct_Id(memberId, productId);
    }
}
