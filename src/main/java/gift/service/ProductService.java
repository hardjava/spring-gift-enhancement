package gift.service;

import gift.dto.*;
import gift.domain.Product;
import gift.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductWithPageResponseDto findAllProducts(int page, int limit, String search) {
        if (page < 0 || limit < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "올바르지 않은 요청입니다.");
        }

        int offset = (page - 1) * limit;
        List<Product> list;
        long totalCount;

        if (search == null || search.isBlank()) {
            list = productRepository.findAllByPaging(limit, offset);
            totalCount = productRepository.countAll();
        } else {
            String keyword = "%" + search + "%";
            list = productRepository.findByKeyword(limit, offset, keyword);
            totalCount = productRepository.countAllByKeyword(keyword);
        }

        List<ProductResponseDto> content = list.stream()
                .map(ProductResponseDto::from)
                .toList();

        PaginationMetadataDto paginationMetadataDto = new PaginationMetadataDto(
                page,
                limit,
                (int) Math.ceil((double) totalCount / limit),
                totalCount
        );

        return new ProductWithPageResponseDto(content, paginationMetadataDto);
    }

    public ProductResponseDto findProductById(Long id) {
        Product findProduct = productRepository.findProductByIdOrElseThrow(id);

        return ProductResponseDto.from(findProduct);
    }

    @Transactional
    public ProductResponseDto createProduct(CreateProductRequestDto requestDto) {
        Product product = Product.of(requestDto.name(), requestDto.price(), requestDto.imageUrl());
        Product createdProduct = productRepository.save(product);

        return ProductResponseDto.from(createdProduct);
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product findProduct = productRepository.findProductByIdOrElseThrow(id);
        productRepository.deleteById(id);
    }

    @Transactional
    public void updateProduct(UpdateProductRequestDto requestDto) {
        Product findProduct = productRepository.findProductByIdOrElseThrow(requestDto.id());
        findProduct.update(requestDto.name(), requestDto.price(), requestDto.imageUrl());
    }
}
