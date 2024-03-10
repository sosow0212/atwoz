package com.atwoz.mission.domain;

import com.atwoz.global.domain.BaseEntity;
import com.atwoz.mission.exception.MissionNotClearException;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode(of = "id", callSuper = false)
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MemberMission extends BaseEntity {

    private static final boolean DEFAULT_CLEAR_STATUS = false;
    private static final boolean CLEAR_STATUS = true;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private Mission mission;

    private boolean isStatusClear;

    public MemberMission(final Mission mission) {
        this.mission = mission;
        this.isStatusClear = DEFAULT_CLEAR_STATUS;
    }

    public void clear() {
        this.isStatusClear = CLEAR_STATUS;
    }

    public boolean isSameMission(final Long missionId) {
        return this.mission.isSameMission(missionId);
    }

    public Integer getReward() {
        if (!isStatusClear) {
            throw new MissionNotClearException();
        }

        return this.mission.getReward();
    }
}
