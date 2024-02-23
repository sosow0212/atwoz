package com.atwoz.member.ui.info;

import static com.atwoz.helper.RestDocsHelper.customDocument;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.atwoz.member.application.info.InfoService;
import com.atwoz.member.application.info.dto.HobbyWriteRequest;
import com.atwoz.member.application.info.dto.InfoWriteRequest;
import com.atwoz.member.application.info.dto.StyleWriteRequest;
import com.atwoz.member.application.info.dto.option.OptionWriteRequest;
import com.atwoz.member.application.info.dto.profile.LocationWriteRequest;
import com.atwoz.member.application.info.dto.profile.PositionWriteRequest;
import com.atwoz.member.application.info.dto.profile.ProfileWriteRequest;
import com.atwoz.member.ui.auth.interceptor.LoginValidCheckerInterceptor;
import com.atwoz.member.ui.auth.interceptor.ParseMemberIdFromTokenInterceptor;
import com.atwoz.member.ui.auth.support.resolver.AuthArgumentResolver;
import com.atwoz.member.ui.auth.support.resolver.OAuthArgumentResolver;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.util.List;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@AutoConfigureRestDocs
@MockBean(JpaMetamodelMappingContext.class)
@WebMvcTest(InfoController.class)
class InfoControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InfoService infoService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthArgumentResolver authArgumentResolver;

    @MockBean
    private ParseMemberIdFromTokenInterceptor parseMemberIdFromTokenInterceptor;

    @MockBean
    private LoginValidCheckerInterceptor loginValidCheckerInterceptor;

    @MockBean
    private OAuthArgumentResolver oAuthArgumentResolver;

    @Test
    void 회원_정보를_작성한다() throws Exception {
        // given
        Long memberId = 1L;

        when(oAuthArgumentResolver.supportsParameter(any())).thenReturn(true);
        when(parseMemberIdFromTokenInterceptor.preHandle(any(), any(), any())).thenReturn(true);
        when(loginValidCheckerInterceptor.preHandle(any(), any(), any())).thenReturn(true);
        when(authArgumentResolver.supportsParameter(any())).thenReturn(true);
        when(authArgumentResolver.resolveArgument(any(), any(), any(), any())).thenReturn(memberId);

        int birthYear = 2000;
        int height = 171;
        String gender = "남성";
        LocationWriteRequest locationWriteRequest = new LocationWriteRequest("서울시", "강남구");
        BigDecimal latitude = BigDecimal.valueOf(70.3);
        BigDecimal longitude = BigDecimal.valueOf(140.3);
        PositionWriteRequest positionWriteRequest = new PositionWriteRequest(latitude, longitude);
        String job = "개발자";
        ProfileWriteRequest profileWriteRequest = new ProfileWriteRequest(
                birthYear,
                height,
                gender,
                locationWriteRequest,
                positionWriteRequest,
                job
        );

        String drink = "전혀 마시지 않음";
        String graduate = "서울 4년제";
        String religion = "기독교";
        String smoke = "비흡연";
        String mbti = "INFJ";
        OptionWriteRequest optionWriteRequest = new OptionWriteRequest(
                drink,
                graduate,
                religion,
                smoke,
                mbti
        );

        List<HobbyWriteRequest> hobbies = List.of(
                new HobbyWriteRequest("와인"),
                new HobbyWriteRequest("요리")
        );

        List<StyleWriteRequest> styles = List.of(
                new StyleWriteRequest("긍정적"),
                new StyleWriteRequest("진중함")
        );

        InfoWriteRequest infoWriteRequest = new InfoWriteRequest(profileWriteRequest, optionWriteRequest, hobbies, styles);

        // when & then
        mockMvc.perform(post("/api/info")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(infoWriteRequest)))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(customDocument("write_info",
                        requestFields(
                                fieldWithPath("profile.birthYear").description("출생년도"),
                                fieldWithPath("profile.height").description("키"),
                                fieldWithPath("profile.gender").description("성별"),
                                fieldWithPath("profile.location.city").description("위치 (시/도)"),
                                fieldWithPath("profile.location.sector").description("위치 (구)"),
                                fieldWithPath("profile.position.latitude").description("위도"),
                                fieldWithPath("profile.position.longitude").description("경도"),
                                fieldWithPath("profile.job").description("직업"),
                                fieldWithPath("option.drink").description("음주 단계"),
                                fieldWithPath("option.graduate").description("최종 학력"),
                                fieldWithPath("option.religion").description("종교"),
                                fieldWithPath("option.smoke").description("흡연 단계"),
                                fieldWithPath("option.mbti").description("MBTI"),
                                fieldWithPath("hobbies").description("취미 목록"),
                                fieldWithPath("hobbies[].hobby").description("취미 이름"),
                                fieldWithPath("styles").description("스타일 목록"),
                                fieldWithPath("styles[].style").description("스타일 이름")
                        )
                ));
    }
}
