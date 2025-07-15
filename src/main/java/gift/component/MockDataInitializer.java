package gift.component;

import gift.domain.Member;
import gift.domain.Product;
import gift.enums.Role;
import gift.repository.MemberRepository;
import gift.repository.ProductRepository;
import gift.repository.WishListRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MockDataInitializer implements CommandLineRunner {
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final WishListRepository wishListRepository;
    private final BCryptEncryptor bCryptEncryptor;

    public MockDataInitializer(MemberRepository memberRepository, ProductRepository productRepository, WishListRepository wishListRepository, BCryptEncryptor bCryptEncryptor) {
        this.memberRepository = memberRepository;
        this.productRepository = productRepository;
        this.wishListRepository = wishListRepository;
        this.bCryptEncryptor = bCryptEncryptor;
    }

    @Override
    public void run(String... args) throws Exception {
        memberRepository.save(new Member("test1@email.com", bCryptEncryptor.encode("1234"), Role.ROLE_USER));
        memberRepository.save(new Member("test2@email.com", bCryptEncryptor.encode("1234"), Role.ROLE_USER));
        memberRepository.save(new Member("test3@email.com", bCryptEncryptor.encode("1234"), Role.ROLE_USER));
        memberRepository.save(new Member("admin@email.com", bCryptEncryptor.encode("5678"), Role.ROLE_ADMIN));

        productRepository.save(new Product("쌍쌍바", 1200L, "test1"));
        productRepository.save(new Product("누가바", 1300L, "test2"));
        productRepository.save(new Product("보석바", 1500L, "test3"));
        productRepository.save(new Product("수박바", 1600L, "test4"));
        productRepository.save(new Product("바밤바", 1100L, "test5"));

//
//        wishListRepository.saveWish(new Wish(1L, 2L));
//        wishListRepository.saveWish(new Wish(1L, 2L));
//        wishListRepository.saveWish(new Wish(1L, 3L));
//        wishListRepository.saveWish(new Wish(1L, 1L));
//        wishListRepository.saveWish(new Wish(1L, 1L));
//        wishListRepository.saveWish(new Wish(1L, 1L));
//
//        wishListRepository.saveWish(new Wish(2L, 1L));
//        wishListRepository.saveWish(new Wish(2L, 3L));
    }
}
