//package gift;
//
//import gift.domain.Product;
//import gift.domain.WishSummary;
//import gift.dto.ProductWithPageResponseDto;
//import gift.dto.WishSummaryWithPageResponseDto;
//import gift.repository.ProductRepository;
//import gift.repository.WishListRepository;
//import gift.service.ProductService;
//import gift.service.WishListService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//
//@ExtendWith(MockitoExtension.class)
//public class PagingTest {
//    @Mock
//    private ProductRepository productRepository;
//
//    @Mock
//    private WishListRepository wishListRepository;
//
//    @InjectMocks
//    private ProductService productService;
//
//    @InjectMocks
//    private WishListService wishListService;
//
//    @Test
//    void 검색어_없이_상품_페이징_정상조회() {
//        // given
//        int page = 1;
//        int limit = 10;
//        Long count = 2L;
//
//        List<Product> mockProducts = List.of(
//                new Product("상품1", 1000L, "img1"),
//                new Product("상품2", 2000L, "img2")
//        );
//
//        Mockito.when(productRepository.findAllByPaging(limit, 0))
//                .thenReturn(mockProducts);
//
//        Mockito.when(productRepository.countAll())
//                .thenReturn(count);
//
//        // when
//        ProductWithPageResponseDto dto = productService.findAllProducts(page, limit, null);
//
//        // then
//        assertThat(dto.content()).hasSize(mockProducts.size());
//        assertThat(dto.paginationMetadata().page()).isEqualTo(page);
//        assertThat(dto.paginationMetadata().limit()).isEqualTo(limit);
//        assertThat(dto.paginationMetadata().totalCount()).isEqualTo(mockProducts.size());
//        assertThat(dto.paginationMetadata().totalPage()).isEqualTo(1);
//    }
//
//    @Test
//    void 검색어_있을때_상품_페이징_정상조회() {
//        // given
//        int page = 1;
//        int limit = 10;
//        Long count = 2L;
//        String search = "상품";
//
//        List<Product> mockProducts = List.of(
//                new Product("상품검색1", 3000L, "img3"),
//                new Product("상품검색2", 4000L, "img4")
//        );
//
//        Mockito.when(productRepository.findByKeyword(limit, 0, "%" + search + "%"))
//                .thenReturn(mockProducts);
//
//        Mockito.when(productRepository.countAllByKeyword("%" + search + "%"))
//                .thenReturn(count);
//
//        // when
//        ProductWithPageResponseDto result = productService.findAllProducts(page, limit, search);
//
//        // then
//        assertThat(result.content()).hasSize(2);
//        assertThat(result.paginationMetadata().totalCount()).isEqualTo(2);
//    }
//
//    @Test
//    void 잘못된_page_limit_입력시_404반환() {
//        // given
//        int page = -1;
//        int limit = 5;
//
//        // then
//        assertThatThrownBy(() -> productService.findAllProducts(page, limit, null))
//                .isInstanceOf(ResponseStatusException.class)
//                .hasMessageContaining(HttpStatus.BAD_REQUEST.name());
//    }
//
//    @Test
//    void 검색어_없이_회원의_위시요약_페이징_정상조회() {
//        // given
//        Long memberId = 1L;
//        int page = 1;
//        int limit = 5;
//        Long count = 2L;
//
//        List<WishSummary> mockSummaries = List.of(
//                new WishSummary("초콜릿", 3L),
//                new WishSummary("꽃다발", 2L)
//        );
//
//        Mockito.when(wishListRepository.findAllWishSummaryByMemberId(memberId, limit, 0))
//                .thenReturn(mockSummaries);
//
//        Mockito.when(wishListRepository.countAll(memberId))
//                .thenReturn(count);
//
//        // when
//        WishSummaryWithPageResponseDto result = wishListService.findAllWishSummaryByMemberId(memberId, page, limit, null);
//
//        // then
//        assertThat(result.content()).hasSize(mockSummaries.size());
//        assertThat(result.paginationMetadata().page()).isEqualTo(page);
//        assertThat(result.paginationMetadata().limit()).isEqualTo(limit);
//        assertThat(result.paginationMetadata().totalCount()).isEqualTo(mockSummaries.size());
//        assertThat(result.paginationMetadata().totalPage()).isEqualTo(1);
//    }
//}
