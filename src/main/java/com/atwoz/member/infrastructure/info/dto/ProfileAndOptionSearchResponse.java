package com.atwoz.member.infrastructure.info.dto;

import com.atwoz.member.domain.info.option.Drink;
import com.atwoz.member.domain.info.option.Graduate;
import com.atwoz.member.domain.info.option.Mbti;
import com.atwoz.member.domain.info.option.Religion;
import com.atwoz.member.domain.info.option.Smoke;
import com.atwoz.member.domain.info.profile.body.Gender;
import com.atwoz.member.domain.info.profile.job.Job;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileAndOptionSearchResponse {

    private int age;
    private int height;
    private Gender gender;
    private Job job;
    private String city;
    private String sector;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Drink drink;
    private Graduate graduate;
    private Mbti mbti;
    private Religion religion;
    private Smoke smoke;
}
