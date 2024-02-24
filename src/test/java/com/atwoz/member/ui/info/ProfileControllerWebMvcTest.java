package com.atwoz.member.ui.info;

import static com.atwoz.helper.RestDocsHelper.customDocument;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.atwoz.helper.MockBeanInjection;
import com.atwoz.member.domain.info.profile.Body;
import com.atwoz.member.domain.info.profile.Job;
import com.atwoz.member.domain.info.profile.Location;
import com.atwoz.member.domain.info.profile.Position;
import com.atwoz.member.domain.info.profile.Profile;
import com.atwoz.member.fixture.info.BodyFixture;
import com.atwoz.member.fixture.info.JobFixture;
import com.atwoz.member.fixture.info.LocationFixture;
import com.atwoz.member.fixture.info.PositionFixture;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@AutoConfigureRestDocs
@WebMvcTest(ProfileController.class)
class ProfileControllerWebMvcTest extends MockBeanInjection {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void 회원의_profile을_조회한다() throws Exception {
        // given
        Long memberId = 1L;
        String bearerToken = "Bearer token";

        Body body = BodyFixture.일반_body_생성();
        Location location = LocationFixture.일반_위치_생성();
        Position position = PositionFixture.일반_좌표_생성();
        Job job = JobFixture.일반_직업_생성();

        Profile savedProfile = new Profile(memberId, body, location, position, job);

        when(profileService.findByMemberId(any())).thenReturn(savedProfile);

        // when & then
        mockMvc.perform(get("/api/info/profile")
                        .header(AUTHORIZATION, bearerToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.age").value(body.getAge()))
                .andExpect(jsonPath("$.height").value(body.getHeight()))
                .andExpect(jsonPath("$.gender").value(body.getGender().getName()))
                .andExpect(jsonPath("$.location.city").value(location.getCity()))
                .andExpect(jsonPath("$.location.sector").value(location.getSector()))
                .andExpect(jsonPath("$.position.latitude").value(position.getLatitude()))
                .andExpect(jsonPath("$.position.longitude").value(position.getLongitude()))
                .andExpect(jsonPath("$.job").value(job.getJob()))
                .andDo(print())
                .andDo(customDocument("search_profile",
                        requestHeaders(
                                headerWithName("Authorization").description("유저 토큰 정보")
                        ),
                        responseFields(
                                fieldWithPath("age").description("나이"),
                                fieldWithPath("height").description("키"),
                                fieldWithPath("gender").description("성별"),
                                fieldWithPath("location.city").description("위치 (시/도)"),
                                fieldWithPath("location.sector").description("위치 (구)"),
                                fieldWithPath("position.latitude").description("위도"),
                                fieldWithPath("position.longitude").description("경도"),
                                fieldWithPath("job").description("직업")
                        )
                ));
    }
}
