package com.atwoz.mission.domain;

import com.atwoz.global.domain.BaseEntity;
import com.atwoz.mission.exception.MissionNotFoundException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode(of = "id", callSuper = false)
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MemberMissions extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<MemberMission> memberMissions = new ArrayList<>();

    public void addMission(final MemberMission memberMission) {
        this.memberMissions.add(memberMission);
    }

    public Integer getRewardBy(final Long missionId) {
        return this.memberMissions.stream()
                .filter(memberMission -> memberMission.isSameMission(missionId))
                .findAny()
                .orElseThrow(MissionNotFoundException::new)
                .getReward();
    }

    public Integer getTotalClearedReward() {
        return this.memberMissions.stream()
                .filter(MemberMission::isStatusClear)
                .mapToInt(MemberMission::getReward)
                .sum();
    }
}
