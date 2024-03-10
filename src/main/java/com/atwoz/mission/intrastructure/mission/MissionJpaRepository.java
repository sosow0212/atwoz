package com.atwoz.mission.intrastructure.mission;

import com.atwoz.mission.domain.mission.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MissionJpaRepository extends JpaRepository<Mission, Long> {

    Optional<Mission> findById(Long id);

    Mission save(Mission mission);

    void deleteById(Long id);
}
