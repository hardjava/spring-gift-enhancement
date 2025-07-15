package gift.repository;

import gift.domain.Member;
import gift.domain.Product;
import gift.enums.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class RepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    void saveMember() {
        var member = new Member("test@email.com", "1234", Role.ROLE_USER);
        assertThat(member.getId()).isNull();
        var savedMember = memberRepository.save(member);
        assertThat(savedMember.getId()).isNotNull();
        assertThat(savedMember.getEmail()).isNotNull();
    }

    @Test
    void findMemberByEmail() {
        var member1 = memberRepository.findMemberByEmail("test@email.com");
        assertThat(member1).isEmpty();
        memberRepository.save(new Member("test@email.com", "1234", Role.ROLE_USER));
        var member2 = memberRepository.findMemberByEmail("test@email.com");
        assertThat(member2).isNotEmpty();
    }

    @Test
    void existsMemberByEmail() {
        assertThat(memberRepository.existsMemberByEmail("test@email.com")).isFalse();
        memberRepository.save(new Member("test@email.com", "1234", Role.ROLE_USER));
        assertThat(memberRepository.existsMemberByEmail("test@email.com")).isTrue();
    }

    @Test
    void saveProduct() {
        var product = new Product("[테스트] 쌍쌍바", 123400L, "테스트url");
        assertThat(product.getId()).isNull();
        var savedProduct = productRepository.save(product);
        assertThat(savedProduct.getId()).isNotNull();
        assertThat(savedProduct.getName()).isNotNull();
    }

    @Test
    void updateProduct() {
        var product = productRepository.save(new Product("[테스트] 쌍쌍바", 123400L, "테스트url"));
        product.update("[테스트] update", 1200L, "수정 URL");
        var product2 = productRepository.findByName("[테스트] update");
        assertThat(product2).isNotEmpty();
    }
}
