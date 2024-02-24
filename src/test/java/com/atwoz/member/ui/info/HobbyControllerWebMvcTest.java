package com.atwoz.member.ui.info;

import static com.atwoz.helper.RestDocsHelper.customDocument;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.atwoz.helper.MockBeanInjection;
import com.atwoz.member.domain.info.hobby.Hobby;
import com.atwoz.member.domain.info.hobby.HobbyName;
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
@WebMvcTest(HobbyController.class)
class HobbyControllerWebMvcTest extends MockBeanInjection {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void 회원의_취미를_조회한다() throws Exception {
        // given
        Long memberId = 1L;

        when(oAuthArgumentResolver.supportsParameter(any())).thenReturn(true);
        when(parseMemberIdFromTokenInterceptor.preHandle(any(), any(), any())).thenReturn(true);
        when(loginValidCheckerInterceptor.preHandle(any(), any(), any())).thenReturn(true);
        when(authArgumentResolver.supportsParameter(any())).thenReturn(true);
        when(authArgumentResolver.resolveArgument(any(), any(), any(), any())).thenReturn(memberId);

        List<Hobby> savedHobbies = List.of(
                new Hobby(memberId, HobbyName.DANCE),
                new Hobby(memberId, HobbyName.ANIMATION)
        );
        when(hobbyService.findMemberHobbies(memberId)).thenReturn(savedHobbies);

        // when & then
        mockMvc.perform(get("/api/info/hobby"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].hobby").value("댄스"))
                .andExpect(jsonPath("$[1].hobby").value("애니메이션"))
                .andDo(print())
                .andDo(customDocument("search_hobbies",
                        responseFields(
                                fieldWithPath("[].hobby").description("취미 이름")
                        )
                ));
    }
}
