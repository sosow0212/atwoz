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
import com.atwoz.member.domain.info.style.Style;
import com.atwoz.member.domain.info.style.StyleName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@AutoConfigureRestDocs
@WebMvcTest(StyleController.class)
class StyleControllerWebMvcTest extends MockBeanInjection {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void 회원의_스타일을_조회한다() throws Exception {
        // given
        Long memberId = 1L;
        String bearerToken = "Bearer token";

        List<Style> savedStyles = List.of(
                new Style(memberId, StyleName.POSITIVE),
                new Style(memberId, StyleName.GENTLE)
        );
        when(styleService.findMemberStyles(any())).thenReturn(savedStyles);

        // when & then
        mockMvc.perform(get("/api/info/style")
                        .header(AUTHORIZATION, bearerToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].style").value("긍정적"))
                .andExpect(jsonPath("$[1].style").value("진중함"))
                .andDo(print())
                .andDo(customDocument("search_styles",
                        requestHeaders(
                                headerWithName("Authorization").description("유저 토큰 정보")
                        ),
                        responseFields(
                                fieldWithPath("[].style").description("스타일 이름")
                        )
                ));
    }
}
