package com.atwoz.member.ui.info.dto;

import com.atwoz.member.domain.info.style.Style;
import com.atwoz.member.domain.info.style.StyleName;

public record StyleSearchResponse(

        String style
) {

    public static StyleSearchResponse from(final Style style) {
        StyleName styleName = style.getStyleName();
        return new StyleSearchResponse(styleName.getCode());
    }
}
