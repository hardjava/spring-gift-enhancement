package gift.dto;

import gift.domain.Product;

import java.util.List;

public record ProductWithPageResponseDto(
        List<ProductResponseDto> content,
        PaginationMetadataDto paginationMetadata
) {
}
