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
import com.atwoz.member.domain.info.option.Drink;
import com.atwoz.member.domain.info.option.Graduate;
import com.atwoz.member.domain.info.option.Mbti;
import com.atwoz.member.domain.info.option.Option;
import com.atwoz.member.domain.info.option.Religion;
import com.atwoz.member.domain.info.option.Smoke;
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
@WebMvcTest(OptionController.class)
class OptionControllerWebMvcTest extends MockBeanInjection {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void 회원의_option을_조회한다() throws Exception {
        // given
        Long memberId = 1L;
        String bearerToken = "Bearer token";

        Smoke smoke = Smoke.NEVER;
        Religion religion = Religion.CHRIST;
        Drink drink = Drink.NEVER;
        Mbti mbti = Mbti.INFJ;
        Graduate graduate = Graduate.SEOUL_FOURTH;
        Option savedOption = new Option(memberId, smoke, religion, drink, mbti, graduate);

        when(optionService.findByMemberId(any())).thenReturn(savedOption);

        // when & then
        mockMvc.perform(get("/api/info/option")
                        .header(AUTHORIZATION, bearerToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.drink").value("전혀 마시지 않음"))
                .andExpect(jsonPath("$.graduate").value("서울 4년제"))
                .andExpect(jsonPath("$.religion").value("기독교"))
                .andExpect(jsonPath("$.smoke").value("비흡연"))
                .andExpect(jsonPath("$.mbti").value("INFJ"))
                .andDo(print())
                .andDo(customDocument("search_option",
                        requestHeaders(
                                headerWithName("Authorization").description("유저 토큰 정보")
                        ),
                        responseFields(
                                fieldWithPath("drink").description("음주 단계"),
                                fieldWithPath("graduate").description("최종 학력"),
                                fieldWithPath("religion").description("종교"),
                                fieldWithPath("smoke").description("흡연 단계"),
                                fieldWithPath("mbti").description("MBTI")
                        )
                ));
    }
}
