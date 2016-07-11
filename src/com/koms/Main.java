// Jetty SSL websocket Example
//
// http://stackoverflow.com/questions/37967362/websocket-over-ssl-in-embedded-jetty-9
//$ keytool -genkeypair -alias certificatekey -keyalg RSA -validity 365 -keystore keystore.jks
//$ keytool -export -alias certificatekey -keystore keystore.jks -rfc -file selfsignedcert.cer
//$ keytool -import -alias certificatekey -file selfsignedcert.cer -keystore truststore.jks
//
// also http://stackoverflow.com/questions/31664366/chrome-failing-to-connect-to-websocket-server-opcode-1-handshake-was-cancele
// don't forget to add your selfsigned serf to browser trusted certs list
package com.koms;
import org.apache.log4j.BasicConfigurator;
import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.util.ssl.SslContextFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args)  throws InterruptedException, IOException {
        //uncomment if you want logs
        //BasicConfigurator.configure();
        System.out.println("Hallo");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Server server = new Server();
        try {
            HttpConfiguration http_config = new HttpConfiguration();
            http_config.setSecureScheme("https");
            http_config.setSecurePort(8081);

            HttpConfiguration https_config = new HttpConfiguration(http_config);
            https_config.addCustomizer(new SecureRequestCustomizer());

            SslContextFactory sslContextFactory = new SslContextFactory();
            sslContextFactory.setKeyStorePath("keystore.jks");
            sslContextFactory.setKeyStorePassword("123456");

            ServerConnector wsConnector = new ServerConnector(server);
            wsConnector.setHost("localhost");
            wsConnector.setPort(8081);
            server.addConnector(wsConnector);

            ServerConnector wssConnector = new ServerConnector(
                    server,
                    new SslConnectionFactory(sslContextFactory, HttpVersion.HTTP_1_1.asString()),
                    new HttpConnectionFactory(https_config)
            );


            server.setHandler(new SocketHandler());

            wssConnector.setHost("localhost");
            wssConnector.setPort(8082);

            server.addConnector(wssConnector);

            server.start();
//            server.join();
            System.out.println("STARTED! try this in browser -> new WebSocket(\"wss://localhost:8082\");");
        } catch (Exception e){
            System.out.println(e.getMessage());
            return;
        }

        while (true) {
            if (System.in.available() > 0)
                if (br.readLine().equals("exit"))
                    break;
        }

        try {
            server.stop();
        } catch (Exception e){
            // i don't actually care at this point
        }

        System.out.println("Stopping");
    }
}
