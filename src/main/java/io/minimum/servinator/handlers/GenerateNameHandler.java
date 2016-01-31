package io.minimum.servinator.handlers;

import io.minimum.servinator.NameList;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GenerateNameHandler extends AbstractHandler {
    private static final String GENROOT = "/generate/";

    @Override
    public void handle(String target, Request request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        // Crudest router evar
        if (target.startsWith(GENROOT)) {
            String list = target.substring(GENROOT.length());
            String name;
            try {
                name = NameList.instance().generateName(list);
            } catch (Throwable throwable) {
                return;
            }
            httpServletResponse.getOutputStream().print(name);
            httpServletResponse.setStatus(200);
            request.setHandled(true);
        }
    }
}
