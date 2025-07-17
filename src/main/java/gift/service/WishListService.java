package gift.service;

import gift.domain.Member;
import gift.domain.Product;
import gift.domain.Wish;
import gift.domain.WishSummary;
import gift.dto.PaginationMetadataDto;
import gift.dto.WishSummaryResponseDto;
import gift.dto.WishSummaryWithPageResponseDto;
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

    public WishSummaryWithPageResponseDto findAllWishSummaryByMemberId(Long memberId, int page, int limit, String search) {
        if (page < 0 || limit < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "올바르지 않은 요청입니다.");
        }

        int offset = (page - 1) * limit;
        List<WishSummary> list;
        long totalCount;

        if (search == null || search.isBlank()) {
            list = wishListRepository.findAllWishSummaryByMemberId(memberId, limit, offset);
        } else {
            String keyword = "%" + search + "%";
            list = wishListRepository.findByKeyword(memberId, limit, offset, keyword);
        }
        totalCount = list.size();

        List<WishSummaryResponseDto> content = list.stream()
                .map(WishSummaryResponseDto::from)
                .toList();

        PaginationMetadataDto paginationMetadataDto = new PaginationMetadataDto(
                page,
                limit,
                (int) Math.ceil((double) totalCount / limit),
                totalCount
        );

        return new WishSummaryWithPageResponseDto(content, paginationMetadataDto);
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
