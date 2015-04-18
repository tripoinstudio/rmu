package com.tripoin.core.rest.json.exception;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class JSONAccessDeniedHandler implements AccessDeniedHandler  {

    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        PrintWriter writer = response.getWriter();
        writer.print("{\"security_user\":null,\"trx_order_header\":null,\"response_code\":\"401\",\"response_msg\":\"Access Denied\",\"result\":\"FAILURE\"}");
    }

}