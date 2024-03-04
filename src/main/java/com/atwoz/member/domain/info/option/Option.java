package com.atwoz.member.domain.info.option;

import com.atwoz.member.domain.info.option.dto.InnerOptionUpdateRequest;
import com.atwoz.member.domain.info.option.dto.InnerOptionWriteRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode(exclude = "id")
@Table(name = "member_option")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Smoke smoke;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Religion religion;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Drink drink;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Mbti mbti;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Graduate graduate;

    public Option(final Long memberId,
                  final Smoke smoke,
                  final Religion religion,
                  final Drink drink,
                  final Mbti mbti,
                  final Graduate graduate) {
        this.memberId = memberId;
        this.smoke = smoke;
        this.religion = religion;
        this.drink = drink;
        this.mbti = mbti;
        this.graduate = graduate;
    }

    public static Option createFrom(final InnerOptionWriteRequest request) {
        return new Option(
                request.memberId(),
                Smoke.findByName(request.smokeName()),
                Religion.findByName(request.religionName()),
                Drink.findByName(request.drinkName()),
                Mbti.findByName(request.mbtiName()),
                Graduate.findByName(request.graduateName())
        );
    }

    public void updateContentsFrom(final InnerOptionUpdateRequest request) {
        this.smoke = Smoke.findByName(request.smokeName());
        this.religion = Religion.findByName(request.religionName());
        this.drink = Drink.findByName(request.drinkName());
        this.mbti = Mbti.findByName(request.mbtiName());
        this.graduate = Graduate.findByName(request.graduateName());
    }
}
