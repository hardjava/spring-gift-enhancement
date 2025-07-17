package gift.controller;

import gift.auth.LoginMember;
import gift.domain.Member;
import gift.dto.WishRequestDto;
import gift.dto.WishSummaryResponseDto;
import gift.dto.WishSummaryWithPageResponseDto;
import gift.service.WishListService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/wishes")
public class WishListController {
    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    // 위시 리스트 상품 조회
    @GetMapping
    public ResponseEntity<WishSummaryWithPageResponseDto> findAllByMemberId(
            @LoginMember Member member,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(required = false) String search
    ) {
        WishSummaryWithPageResponseDto responseDto = wishListService.findAllWishSummaryByMemberId(member.getId(), page, limit, search);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 위시 리스트 상품 등록
    @PostMapping
    public ResponseEntity<Void> saveWish(@Valid @RequestBody WishRequestDto requestDto, @LoginMember Member member) {
        wishListService.saveWish(member, requestDto.productId());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 위시 리스트 상품 삭제
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteWish(@PathVariable Long productId, @LoginMember Member member) {
        wishListService.deleteWish(member.getId(), productId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
