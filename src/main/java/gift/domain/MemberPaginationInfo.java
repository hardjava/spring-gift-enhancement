package gift.domain;

import org.springframework.data.domain.Pageable;

public class MemberPaginationInfo extends PaginationInfo {
    private Member member;

    public MemberPaginationInfo(Pageable pageable, String search, Member member) {
        super(pageable, search);
        this.member = member;
    }

    public Long getMemberId() {
        return member.getId();
    }
}
