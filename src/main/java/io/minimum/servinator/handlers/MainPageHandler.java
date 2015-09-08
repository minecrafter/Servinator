package io.minimum.servinator.handlers;

import com.google.common.io.ByteStreams;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainPageHandler extends AbstractHandler {
    private static final String ROOT = "/";
    private static final String INDEX_PAGE;

    static {
        try {
            INDEX_PAGE = new String(ByteStreams.toByteArray(ClassLoader.getSystemClassLoader().getResourceAsStream("page.html")), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    @Override
    public void handle(String target, Request request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        if (target.equals(ROOT)) {
            httpServletResponse.getOutputStream().println(INDEX_PAGE);
            httpServletResponse.setStatus(200);
            request.setHandled(true);
        }
    }
}
