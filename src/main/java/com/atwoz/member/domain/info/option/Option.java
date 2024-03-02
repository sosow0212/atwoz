package com.atwoz.member.domain.info.option;

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
        updateContents(smoke, religion, drink, mbti, graduate);
    }

    public void updateContents(final Smoke smoke,
                               final Religion religion,
                               final Drink drink,
                               final Mbti mbti,
                               final Graduate graduate) {
        this.smoke = smoke;
        this.religion = religion;
        this.drink = drink;
        this.mbti = mbti;
        this.graduate = graduate;
    }

    public static Option of(final Long memberId,
                            final String smokeName,
                            final String religionName,
                            final String drinkName,
                            final String mbtiName,
                            final String graduateName) {
        Smoke smoke = Smoke.findByName(smokeName);
        Religion religion = Religion.findByName(religionName);
        Drink drink = Drink.findByName(drinkName);
        Mbti mbti = Mbti.findByName(mbtiName);
        Graduate graduate = Graduate.findByName(graduateName);

        return new Option(memberId, smoke, religion, drink, mbti, graduate);
    }
}
