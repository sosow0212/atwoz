package com.atwoz.member.domain.info.profile;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member_profile")
@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private MemberBody memberBody;

    @Embedded
    @Column(nullable = false)
    private Location location;

    @Embedded
    @Column(nullable = false)
    private Job job;

    public Profile(final Long memberId, final MemberBody memberBody, final Location location, final Job job) {
        this.memberId = memberId;
        updateContents(memberBody, location, job);
    }

    public void updateContents(final MemberBody memberBody, final Location location, final Job job) {
        this.memberBody = memberBody;
        this.location = location;
        this.job = job;
    }
}
