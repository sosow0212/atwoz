package com.atwoz.member.ui.info;

import static com.atwoz.helper.RestDocsHelper.customDocument;
import static com.atwoz.member.fixture.info.dto.request.InfoRequestFixture.회원_정보_생성_요청;
import static com.atwoz.member.fixture.info.dto.request.InfoRequestFixture.회원_정보_수정_요청;
import static com.atwoz.member.fixture.info.dto.response.InfoSearchResponseFixture.회원_정보_조회_응답;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.atwoz.helper.MockBeanInjection;
import com.atwoz.member.application.info.dto.InfoUpdateRequest;
import com.atwoz.member.application.info.dto.InfoWriteRequest;
import com.atwoz.member.ui.info.dto.InfoSearchResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@AutoConfigureRestDocs
@WebMvcTest(InfoController.class)
class InfoControllerWebMvcTest extends MockBeanInjection {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void 회원_정보를_작성한다() throws Exception {
        // given
        String bearerToken = "Bearer token";
        InfoWriteRequest infoWriteRequest = 회원_정보_생성_요청();

        // when & then
        mockMvc.perform(post("/api/info")
                        .header(AUTHORIZATION, bearerToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(infoWriteRequest)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andDo(customDocument("write_info",
                        requestHeaders(
                                headerWithName("Authorization").description("유저 토큰 정보")
                        ),
                        requestFields(
                                fieldWithPath("profile.body").description("신체 정보"),
                                fieldWithPath("profile.body.birthYear").description("출생년도"),
                                fieldWithPath("profile.body.height").description("키"),
                                fieldWithPath("profile.body.gender").description("성별"),
                                fieldWithPath("profile.location.city").description("위치 (시/도)"),
                                fieldWithPath("profile.location.sector").description("위치 (구/군)"),
                                fieldWithPath("profile.position.latitude").description("좌표 (위도)"),
                                fieldWithPath("profile.position.longitude").description("좌표 (경도)"),
                                fieldWithPath("profile.job").description("직업 코드"),
                                fieldWithPath("option.drink").description("음주 단계"),
                                fieldWithPath("option.graduate").description("최종 학력"),
                                fieldWithPath("option.religion").description("종교"),
                                fieldWithPath("option.smoke").description("흡연 단계"),
                                fieldWithPath("option.mbti").description("MBTI"),
                                fieldWithPath("hobbies").description("취미 목록"),
                                fieldWithPath("hobbies[].hobby").description("취미 코드"),
                                fieldWithPath("styles").description("스타일 목록"),
                                fieldWithPath("styles[].style").description("스타일 코드")
                        )
                ));
    }

    @Test
    void 회원_정보를_수정한다() throws Exception {
        // given
        String bearerToken = "Bearer token";
        InfoUpdateRequest infoUpdateRequest = 회원_정보_수정_요청();

        // when & then
        mockMvc.perform(patch("/api/info")
                        .header(AUTHORIZATION, bearerToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(infoUpdateRequest)))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(customDocument("update_info",
                        requestHeaders(
                                headerWithName("Authorization").description("유저 토큰 정보")
                        ),
                        requestFields(
                                fieldWithPath("profile.body").description("신체 정보"),
                                fieldWithPath("profile.body.birthYear").description("출생년도"),
                                fieldWithPath("profile.body.height").description("키"),
                                fieldWithPath("profile.body.gender").description("성별"),
                                fieldWithPath("profile.location.city").description("위치 (시/도)"),
                                fieldWithPath("profile.location.sector").description("위치 (구/군)"),
                                fieldWithPath("profile.position.latitude").description("좌표 (위도)"),
                                fieldWithPath("profile.position.longitude").description("좌표 (경도)"),
                                fieldWithPath("profile.job").description("직업 코드"),
                                fieldWithPath("option.drink").description("음주 단계"),
                                fieldWithPath("option.graduate").description("최종 학력"),
                                fieldWithPath("option.religion").description("종교"),
                                fieldWithPath("option.smoke").description("흡연 단계"),
                                fieldWithPath("option.mbti").description("MBTI"),
                                fieldWithPath("hobbies").description("취미 목록"),
                                fieldWithPath("hobbies[].hobby").description("취미 코드"),
                                fieldWithPath("styles").description("스타일 목록"),
                                fieldWithPath("styles[].style").description("스타일 코드")
                        )
                ));
    }

    @Test
    void 회원_정보를_조회한다() throws Exception {
        // given
        String bearerToken = "Bearer token";
        InfoSearchResponse infoSearchResponse = 회원_정보_조회_응답();

        when(infoService.findInfo(any())).thenReturn(infoSearchResponse);

        // when & then
        mockMvc.perform(get("/api/info")
                        .header(AUTHORIZATION, bearerToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("profile").exists())
                .andExpect(jsonPath("profile.body").exists())
                .andExpect(jsonPath("profile.body.age").exists())
                .andExpect(jsonPath("profile.body.height").exists())
                .andExpect(jsonPath("profile.body.gender").exists())
                .andExpect(jsonPath("profile.location").exists())
                .andExpect(jsonPath("profile.location.city").exists())
                .andExpect(jsonPath("profile.location.sector").exists())
                .andExpect(jsonPath("profile.position").exists())
                .andExpect(jsonPath("profile.position.latitude").exists())
                .andExpect(jsonPath("profile.position.longitude").exists())
                .andExpect(jsonPath("profile.job").exists())
                .andExpect(jsonPath("option").exists())
                .andExpect(jsonPath("option.drink").exists())
                .andExpect(jsonPath("option.graduate").exists())
                .andExpect(jsonPath("option.religion").exists())
                .andExpect(jsonPath("option.smoke").exists())
                .andExpect(jsonPath("option.mbti").exists())
                .andExpect(jsonPath("hobbies").exists())
                .andExpect(jsonPath("hobbies[0].hobby").exists())
                .andExpect(jsonPath("styles").exists())
                .andExpect(jsonPath("styles[0].style").exists())
                .andDo(print())
                .andDo(customDocument("search_info",
                        requestHeaders(
                                headerWithName("Authorization").description("유저 토큰 정보")
                        ),
                        responseFields(
                                fieldWithPath("profile.body").description("신체 정보"),
                                fieldWithPath("profile.body.age").description("나이"),
                                fieldWithPath("profile.body.height").description("키"),
                                fieldWithPath("profile.body.gender").description("성별"),
                                fieldWithPath("profile.location.city").description("위치 (시/도)"),
                                fieldWithPath("profile.location.sector").description("위치 (구/군)"),
                                fieldWithPath("profile.position.latitude").description("좌표 (위도)"),
                                fieldWithPath("profile.position.longitude").description("좌표 (경도)"),
                                fieldWithPath("profile.job").description("직업 코드"),
                                fieldWithPath("option.drink").description("음주 단계"),
                                fieldWithPath("option.graduate").description("최종 학력"),
                                fieldWithPath("option.religion").description("종교"),
                                fieldWithPath("option.smoke").description("흡연 단계"),
                                fieldWithPath("option.mbti").description("MBTI"),
                                fieldWithPath("hobbies").description("취미 목록"),
                                fieldWithPath("hobbies[].hobby").description("취미 코드"),
                                fieldWithPath("styles").description("스타일 목록"),
                                fieldWithPath("styles[].style").description("스타일 코드")
                        )
                ));
    }
}
