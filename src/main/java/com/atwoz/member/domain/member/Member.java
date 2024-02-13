package com.atwoz.member.domain.member;

import com.atwoz.global.domain.BaseEntity;
import com.atwoz.member.domain.profile.hobby.Hobbies;
import com.atwoz.member.domain.profile.image.Images;
import com.atwoz.member.domain.profile.style.Styles;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@EqualsAndHashCode(of = "id", callSuper = false)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private MemberRole memberRole;

    @Embedded
    private Hobbies hobbies;

    @Embedded
    private Styles styles;

    @Embedded
    private Images images;

    public boolean isAdmin() {
        return this.memberRole.isAdministrator();
    }

    public static Member createDefaultRole(final String email,
                                           final NicknameGenerator nicknameGenerator) {
        return Member.builder()
                .email(email)
                .nickname(nicknameGenerator.createRandomNickname())
                .memberRole(MemberRole.MEMBER)
                .build();
    }

    public static Member createWithOAuthLogin(final String email,
                                              final String nickname) {

        return Member.builder()
                .email(email)
                .nickname(nickname)
                .memberRole(MemberRole.MEMBER)
                .build();
    }
}
