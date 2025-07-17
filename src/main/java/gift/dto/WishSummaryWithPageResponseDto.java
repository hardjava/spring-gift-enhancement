package gift.dto;

import java.util.List;

public record WishSummaryWithPageResponseDto(
        List<WishSummaryResponseDto> content,
        PaginationMetadataDto paginationMetadata
) {
}
