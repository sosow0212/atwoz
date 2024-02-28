package com.atwoz.member.ui.info;

import static com.atwoz.helper.RestDocsHelper.customDocument;
import static com.atwoz.member.domain.info.hobby.Hobby.COOK;
import static com.atwoz.member.domain.info.hobby.Hobby.WINE;
import static com.atwoz.member.domain.info.style.Style.GENTLE;
import static com.atwoz.member.domain.info.style.Style.POSITIVE;
import static com.atwoz.member.fixture.info.dto.request.OptionWriteRequestFixture.옵션_생성_요청;
import static com.atwoz.member.fixture.info.dto.request.ProfileWriteRequestFixture.프로필_생성_요청;
import static com.atwoz.member.fixture.info.dto.response.OptionSearchResponseFixture.옵션_정보_조회_응답;
import static com.atwoz.member.fixture.info.dto.response.ProfileSearchResponseFixture.프로필_정보_조회_응답;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.atwoz.helper.MockBeanInjection;
import com.atwoz.member.application.info.InfoService;
import com.atwoz.member.application.info.dto.HobbyWriteRequest;
import com.atwoz.member.application.info.dto.InfoWriteRequest;
import com.atwoz.member.application.info.dto.StyleWriteRequest;
import com.atwoz.member.application.info.dto.option.OptionWriteRequest;
import com.atwoz.member.application.info.dto.profile.ProfileWriteRequest;
import com.atwoz.member.ui.info.dto.HobbySearchResponse;
import com.atwoz.member.ui.info.dto.InfoSearchResponse;
import com.atwoz.member.ui.info.dto.StyleSearchResponse;
import com.atwoz.member.ui.info.dto.option.OptionSearchResponse;
import com.atwoz.member.ui.info.dto.profile.ProfileSearchResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@AutoConfigureRestDocs
@WebMvcTest(InfoController.class)
class InfoControllerWebMvcTest extends MockBeanInjection {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InfoService infoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void 회원_정보를_작성한다() throws Exception {
        // given
        String bearerToken = "Bearer token";

        ProfileWriteRequest profileWriteRequest = 프로필_생성_요청();
        OptionWriteRequest optionWriteRequest = 옵션_생성_요청();

        List<HobbyWriteRequest> hobbies = List.of(
                new HobbyWriteRequest(WINE.getCode()),
                new HobbyWriteRequest(COOK.getCode())
        );

        List<StyleWriteRequest> styles = List.of(
                new StyleWriteRequest(POSITIVE.getCode()),
                new StyleWriteRequest(GENTLE.getCode())
        );

        InfoWriteRequest infoWriteRequest = new InfoWriteRequest(profileWriteRequest, optionWriteRequest, hobbies, styles);

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
                                fieldWithPath("profile.birthYear").description("출생년도"),
                                fieldWithPath("profile.height").description("키"),
                                fieldWithPath("profile.gender").description("성별"),
                                fieldWithPath("profile.location.city").description("위치 (시/도)"),
                                fieldWithPath("profile.location.sector").description("위치 (구)"),
                                fieldWithPath("profile.position.latitude").description("위도"),
                                fieldWithPath("profile.position.longitude").description("경도"),
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

        ProfileSearchResponse profileSearchResponse = 프로필_정보_조회_응답();
        OptionSearchResponse optionSearchResponse = 옵션_정보_조회_응답();

        List<HobbySearchResponse> hobbies = List.of(
                new HobbySearchResponse(WINE.getCode()),
                new HobbySearchResponse(COOK.getCode())
        );

        List<StyleSearchResponse> styles = List.of(
                new StyleSearchResponse(POSITIVE.getCode()),
                new StyleSearchResponse(GENTLE.getCode())
        );

        InfoSearchResponse infoSearchResponse = new InfoSearchResponse(profileSearchResponse, optionSearchResponse, hobbies, styles);

        when(infoService.findInfo(any())).thenReturn(infoSearchResponse);

        // when & then
        mockMvc.perform(get("/api/info")
                        .header(AUTHORIZATION, bearerToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("profile").exists())
                .andExpect(jsonPath("profile.height").exists())
                .andExpect(jsonPath("profile.gender").exists())
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
                                fieldWithPath("profile.age").description("나이"),
                                fieldWithPath("profile.height").description("키"),
                                fieldWithPath("profile.gender").description("성별"),
                                fieldWithPath("profile.location.city").description("위치 (시/도)"),
                                fieldWithPath("profile.location.sector").description("위치 (구)"),
                                fieldWithPath("profile.position.latitude").description("위도"),
                                fieldWithPath("profile.position.longitude").description("경도"),
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
