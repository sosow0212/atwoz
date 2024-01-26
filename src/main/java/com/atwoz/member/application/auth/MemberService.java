package com.atwoz.member.application.auth;

import com.atwoz.member.domain.auth.RegisteredEvent;
import com.atwoz.member.domain.member.Member;
import com.atwoz.member.domain.member.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void getMember(final RegisteredEvent event) {
        Optional<Member> findMemberByEmail = memberRepository.findByEmail(event.getEmail());

        if (isNotRegistered(findMemberByEmail)) {
            Member member = Member.createWithOAuthLogin(event.getEmail(), event.getNickname());
            memberRepository.save(member);
        }
    }

    private boolean isNotRegistered(final Optional<Member> member) {
        return member.isEmpty();
    }
}
