package com.atwoz.member.domain.info.profile;

import com.atwoz.member.domain.info.profile.body.Body;
import com.atwoz.member.application.info.profile.dto.InnerProfileUpdateRequest;
import com.atwoz.member.application.info.profile.dto.InnerProfileWriteRequest;
import com.atwoz.member.domain.info.profile.location.Location;
import com.atwoz.member.domain.info.profile.position.Position;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
@Table(name = "member_profile")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private Body body;

    @Embedded
    @Column(nullable = false)
    private Location location;

    @Embedded
    @Column(nullable = false)
    private Position position;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Job job;

    public Profile(final Long memberId,
                   final Body body,
                   final Location location,
                   final Position position,
                   final Job job) {
        this.memberId = memberId;
        updateContents(body, location, position, job);
    }

    public void updateContents(final Body body, final Location location, final Position position, final Job job) {
        this.body = body;
        this.location = location;
        this.position = position;
        this.job = job;
    }

    public static Profile createFrom(final InnerProfileWriteRequest request) {
        Body body = Body.createFrom(request.body());
        Location location = Location.createFrom(request.location());
        Position position = Position.createFrom(request.position());
        Job job = Job.findByCode(request.jobCode());

        return new Profile(request.memberId(), body, location, position, job);
    }

    public void updateContentsFrom(final InnerProfileUpdateRequest request) {
        this.body = Body.updateContentsFrom(request.body());
        this.location = Location.updateContentsFrom(request.location());
        this.position = Position.updateContentsFrom(request.position());
        this.job = Job.findByCode(request.jobCode());
    }
}
