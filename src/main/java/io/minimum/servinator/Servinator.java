package io.minimum.servinator;

import org.eclipse.jetty.server.Server;

public class Servinator {
    public static void main(String... args) throws Exception{
        Server server = new Server(8000);
        server.setHandler(new ServinatorHandler());
        server.start();
    }
}
