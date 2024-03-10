package com.atwoz.mission.ui;

import com.atwoz.helper.MockBeanInjection;
import com.atwoz.mission.application.mission.dto.MissionCreateRequest;
import com.atwoz.mission.domain.mission.Mission;
import com.atwoz.mission.domain.mission.dto.MissionPagingResponse;
import com.atwoz.mission.domain.mission.dto.MissionSimpleResponse;
import com.atwoz.mission.domain.mission.vo.MissionType;
import com.atwoz.mission.domain.mission.vo.PublicOption;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static com.atwoz.helper.RestDocsHelper.customDocument;
import static com.atwoz.mission.fixture.MissionFixture.미션_생성_리워드_100_데일리_공개;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.partWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParts;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@AutoConfigureRestDocs
@WebMvcTest
class MissionControllerWebMvcTest extends MockBeanInjection {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void 미션을_생성한다() throws Exception {
        // given
        MissionCreateRequest request = new MissionCreateRequest("미션 내용", "DAILY", 100, "PUBLIC");

        when(missionService.createNewMission(request)).thenReturn(1L);

        // when & then
        mockMvc.perform(post("/api/missions")
                        .header(HttpHeaders.AUTHORIZATION, "bearer tokenInfo...")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                ).andExpect(status().isCreated())
                .andDo(customDocument("미션_생성",
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("유저 토큰 정보")
                        ),
                        requestFields(
                                fieldWithPath("title").description("미션 내용"),
                                fieldWithPath("missionType").description("미션 타입 = daily / challenge"),
                                fieldWithPath("reward").description("미션 클리어 보상 하트 개수"),
                                fieldWithPath("publicOption").description("공개 여부 = public / private")
                        ),
                        responseHeaders(
                                headerWithName("location").description("미션 생성 후 상세 정보 uri")
                        )
                ));
    }

    @Test
    void 미션을_제거한다() throws Exception {
        // given
        doNothing().when(missionService).deleteMissionById(any());

        // when & then
        mockMvc.perform(delete("/api/missions/{missionId}", 1L)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer tokenInfo...")
                ).andExpect(status().isNoContent())
                .andDo(customDocument("미션_제거",
                        requestHeaders(
                                headerWithName(AUTHORIZATION).description("유저 토큰 정보")
                        ),
                        pathParameters(
                                parameterWithName("missionId").description("미션 id")
                        )
                ));
    }

    @Test
    void 미션을_id로_찾는다() throws Exception {
        // given
        Mission mission = 미션_생성_리워드_100_데일리_공개();
        when(missionQueryService.findMissionDetail(anyLong())).thenReturn(mission);

        // when & then
        mockMvc.perform(get("/api/missions/{missionId}", 1L)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer tokenInfo...")
                ).andExpect(status().isOk())
                .andDo(customDocument("미션_단건_조회",
                        requestHeaders(
                                headerWithName(AUTHORIZATION).description("유저 토큰 정보")
                        ),
                        responseFields(
                                fieldWithPath("id").description("미션 id"),
                                fieldWithPath("title").description("미션 내용"),
                                fieldWithPath("missionType").description("미션 타입 = daily / challenge"),
                                fieldWithPath("reward").description("미션 보상으로 받을 하트"),
                                fieldWithPath("isPublic").description("미션 공개 여부 = public / private"),
                                fieldWithPath("createdDate").description("미션 생성일")
                        )
                ));
    }

    @Test
    void 미션_페이징_조회() throws Exception {
        // given
        MissionSimpleResponse detail = new MissionSimpleResponse(1L, "미션 내용", MissionType.DAILY, 100, PublicOption.PUBLIC, LocalDateTime.now());
        List<MissionSimpleResponse> details = List.of(detail);
        when(missionQueryService.findMissions(any(Pageable.class))).thenReturn(new MissionPagingResponse(details, 1));

        // when & then
        mockMvc.perform(get("/api/missions")
                        .param("page", "0")
                        .param("size", "10")
                        .header(AUTHORIZATION, "Bearer tokenInfo...")
                ).andExpect(status().isOk())
                .andDo(customDocument("전체_미션_페이징조회",
                        requestHeaders(
                                headerWithName(AUTHORIZATION).description("유저 토큰 정보")
                        ),
                        requestParts(
                                partWithName("page").description("페이지 번호").optional(),
                                partWithName("size").description("몇 개씩 조회를 할 것인지").optional()
                        ),
                        responseFields(
                                fieldWithPath("missions[].id").description("미션 id"),
                                fieldWithPath("missions[].title").description("미션 내용"),
                                fieldWithPath("missions[].missionType").description("미션 타입 = daily / challenge"),
                                fieldWithPath("missions[].reward").description("미션 보상으로 받는 하트 개수"),
                                fieldWithPath("missions[].publicOption").description("미션 공개 여부 = public / private"),
                                fieldWithPath("missions[].createdDate").description("미션 생성 날짜"),
                                fieldWithPath("nextPage").description("다음 페이지가 존재하면 1, 없다면 -1")
                        )
                ));
    }
}
