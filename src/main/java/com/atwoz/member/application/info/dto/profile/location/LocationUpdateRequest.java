package com.atwoz.member.application.info.dto.profile.location;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LocationUpdateRequest implements LocationRequest {

        @NotBlank(message = "시/도가 작성되어야 합니다.")
        private String city;

        @NotBlank(message = "구/군이 작성되어야 합니다.")
        private String sector;
}
