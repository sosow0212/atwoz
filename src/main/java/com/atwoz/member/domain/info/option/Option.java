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
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
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

    public Option(final Long memberId, final String smoke, final String religion,
                  final String drink, final String mbti, final String graduate) {
        this.memberId = memberId;
        this.smoke = Smoke.findByName(smoke);
        this.religion = Religion.findByName(religion);
        this.drink = Drink.findByName(drink);
        this.mbti = Mbti.findByName(mbti);
        this.graduate = Graduate.findByName(graduate);
    }
}
