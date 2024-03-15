package com.atwoz.mission.ui.membermission;

import com.atwoz.helper.MockBeanInjection;
import com.atwoz.mission.ui.membermission.dto.RewardResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static com.atwoz.helper.RestDocsHelper.customDocument;
import static com.atwoz.mission.fixture.RewardResponseFixture.회원_보상_응답;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@AutoConfigureRestDocs
@WebMvcTest(MemberMissionsController.class)
public class MemberMissionsControllerWebMvcTest extends MockBeanInjection {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void 회원의_미션_목록을_미션과_함께_생성한다() throws Exception {
        // given
        String bearerToken = "Bearer token";
        Long missionId = 1L;

        doNothing().when(memberMissionsService).addMemberMission(any(), any());

        // when & then
        mockMvc.perform(post("/api/members/me/missions/{missionId}", missionId)
                        .header(AUTHORIZATION, bearerToken))
                .andExpect(status().isCreated())
                .andDo(print())
                .andDo(customDocument("회원_미션과_목록_생성",
                        requestHeaders(
                                headerWithName("Authorization").description("유저 토큰 정보")
                        ),
                        pathParameters(
                                parameterWithName("missionId").description("미션 id")
                        )
                ));
    }

    @Test
    void 회원의_미션_목록의_미션을_클리어한다() throws Exception {
        // given
        String bearerToken = "Bearer token";
        Long missionId = 1L;

        doNothing().when(memberMissionsService).clearMemberMission(any(), any());

        // when & then
        mockMvc.perform(patch("/api/members/me/missions/{missionId}", missionId)
                        .header(AUTHORIZATION, bearerToken))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(customDocument("회원_미션_클리어",
                        requestHeaders(
                                headerWithName("Authorization").description("유저 토큰 정보")
                        ),
                        pathParameters(
                                parameterWithName("missionId").description("미션 id")
                        )
                ));
    }

    @Test
    void 회원의_완료된_미션의_보상을_조회한다() throws Exception {
        // given
        String bearerToken = "Bearer token";
        Long missionId = 1L;
        RewardResponse rewardResponse = 회원_보상_응답();

        when(memberMissionsService.getRewardByMissionId(any(), any())).thenReturn(rewardResponse.reward());

        // when & then
        mockMvc.perform(get("/api/members/me/missions/{missionId}", missionId)
                        .header(AUTHORIZATION, bearerToken))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(customDocument("회원_특정_미션_보상",
                        requestHeaders(
                                headerWithName("Authorization").description("유저 토큰 정보")
                        ),
                        pathParameters(
                                parameterWithName("missionId").description("미션 id")
                        ),
                        responseFields(
                                fieldWithPath("reward").description("미션의 보상")
                        )
                ));
    }

    @Test
    void 회원의_완료된_미션_목록의_보상_총합을_조회한다() throws Exception {
        // given
        String bearerToken = "Bearer token";
        RewardResponse rewardResponse = 회원_보상_응답();

        when(memberMissionsService.getAllClearMissionsRewards(any())).thenReturn(rewardResponse.reward());

        // when & then
        mockMvc.perform(get("/api/members/me/missions/all")
                        .header(AUTHORIZATION, bearerToken))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(customDocument("회원_전체_보상",
                        requestHeaders(
                                headerWithName("Authorization").description("유저 토큰 정보")
                        ),
                        responseFields(
                                fieldWithPath("reward").description("미션의 보상")
                        )
                ));
    }
}
