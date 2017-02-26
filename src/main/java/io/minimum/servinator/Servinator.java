package io.minimum.servinator;

import io.minimum.servinator.handlers.GenerateNameHandler;
import io.minimum.servinator.handlers.MainPageHandler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;

import java.net.InetSocketAddress;

public class Servinator {
    public static void main(String... args) throws Exception {
        String port = System.getenv("PORT");
        Server server = new Server(port == null ? 8080 : Integer.parseInt(port));

        HandlerList handlerList = new HandlerList();
        handlerList.addHandler(new GenerateNameHandler());
        handlerList.addHandler(new MainPageHandler());

        server.setHandler(handlerList);
        server.start();
    }
}
