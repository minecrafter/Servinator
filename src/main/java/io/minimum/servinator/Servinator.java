package io.minimum.servinator;

import io.minimum.servinator.handlers.GenerateNameHandler;
import io.minimum.servinator.handlers.MainPageHandler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;

import java.net.InetSocketAddress;

public class Servinator {
    public static void main(String... args) throws Exception {
        if (args.length != 1) {
            System.out.println("java -jar ... <host:port>");
            return;
        }

        InetSocketAddress socketAddress = getAddr(args[0]);

        Server server = new Server(socketAddress);

        HandlerList handlerList = new HandlerList();
        handlerList.addHandler(new GenerateNameHandler());
        handlerList.addHandler(new MainPageHandler());

        server.setHandler(handlerList);
        server.start();
    }

    private static InetSocketAddress getAddr(String hostline)
    {
        String[] split = hostline.split( ":" );
        int port = 8000;
        if ( split.length > 1 )
        {
            port = Integer.parseInt( split[1] );
        }
        return new InetSocketAddress( split[0], port );
    }
}
