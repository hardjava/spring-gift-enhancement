package gift.repository;

import gift.domain.Member;
import gift.enums.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    void save() {
        var member = new Member("test@email.com", "1234", Role.ROLE_USER);
        assertThat(member.getId()).isNull();
        var savedMember = memberRepository.save(member);
        assertThat(savedMember.getId()).isNotNull();
        assertThat(savedMember.getEmail()).isNotNull();
    }

    @Test
    void findByEmail() {
        var member1 = memberRepository.findMemberByEmail("test@email.com");
        assertThat(member1).isEmpty();
        memberRepository.save(new Member("test@email.com", "1234", Role.ROLE_USER));
        var member2 = memberRepository.findMemberByEmail("test@email.com");
        assertThat(member2).isNotEmpty();
    }

    @Test
    void existsByEmail() {
        assertThat(memberRepository.existsMemberByEmail("test@email.com")).isFalse();
        memberRepository.save(new Member("test@email.com", "1234", Role.ROLE_USER));
        assertThat(memberRepository.existsMemberByEmail("test@email.com")).isTrue();
    }
}
