//package gift.component;
//
//import gift.domain.Member;
//import gift.domain.Product;
//import gift.domain.Wish;
//import gift.enums.Role;
//import gift.repository.MemberRepository;
//import gift.repository.ProductRepository;
//import gift.repository.WishListRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MockDataInitializer implements CommandLineRunner {
//    private final MemberRepository memberRepository;
//    private final ProductRepository productRepository;
//    private final WishListRepository wishListRepository;
//    private final BCryptEncryptor bCryptEncryptor;
//
//    public MockDataInitializer(MemberRepository memberRepository, ProductRepository productRepository, WishListRepository wishListRepository, BCryptEncryptor bCryptEncryptor) {
//        this.memberRepository = memberRepository;
//        this.productRepository = productRepository;
//        this.wishListRepository = wishListRepository;
//        this.bCryptEncryptor = bCryptEncryptor;
//    }
//
//    @Override
//    public void run(String... args) {
//        Member m1 = memberRepository.save(new Member("test1@email.com", bCryptEncryptor.encode("1234"), Role.ROLE_USER));
//        Member m2 = memberRepository.save(new Member("test2@email.com", bCryptEncryptor.encode("1234"), Role.ROLE_USER));
//        Member m3 = memberRepository.save(new Member("test3@email.com", bCryptEncryptor.encode("1234"), Role.ROLE_USER));
//        Member m4 = memberRepository.save(new Member("admin@email.com", bCryptEncryptor.encode("5678"), Role.ROLE_ADMIN));
//
//        Product p1 = productRepository.save(new Product("쌍쌍바", 1200L, "test1"));
//        Product p2 = productRepository.save(new Product("누가바", 1300L, "test2"));
//        Product p3 = productRepository.save(new Product("보석바", 1500L, "test3"));
//        Product p4 = productRepository.save(new Product("수박바", 1600L, "test4"));
//        Product p5 = productRepository.save(new Product("바밤바", 1100L, "test5"));
//
//        for (int i = 0; i < 10; i++) {
//            Product p = productRepository.save(
//                    new Product(
//                            "테스트 데이터 " + i,
//                            1200L,
//                            "테스트 url " + i
//                    )
//            );
//
//            wishListRepository.save(new Wish(m1, p));
//        }
//
//        wishListRepository.save(new Wish(m1, p2));
//        wishListRepository.save(new Wish(m1, p2));
//        wishListRepository.save(new Wish(m1, p3));
//        wishListRepository.save(new Wish(m1, p1));
//        wishListRepository.save(new Wish(m1, p1));
//        wishListRepository.save(new Wish(m1, p1));
//
//        wishListRepository.save(new Wish(m2, p3));
//        wishListRepository.save(new Wish(m2, p3));
//    }
//}
