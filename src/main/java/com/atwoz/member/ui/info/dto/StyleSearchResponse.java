package com.atwoz.member.ui.info.dto;

import com.atwoz.member.domain.info.style.MemberStyle;
import com.atwoz.member.domain.info.style.Style;

public record StyleSearchResponse(

        String style
) {

    public static StyleSearchResponse from(final MemberStyle memberStyle) {
        Style style = memberStyle.getStyle();
        return new StyleSearchResponse(style.getCode());
    }
}
