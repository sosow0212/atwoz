package com.atwoz.member.ui.auth.support.resolver;

import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface RequestProcessor {

    String readJsonFromBody(HttpServletRequest httpServletRequest) throws IOException;
}
