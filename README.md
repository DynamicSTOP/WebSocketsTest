# WebSocketsTest

Just an example of how things can work together.

In order to connect you will need to:

1. Generate certs. unix example:

  `$ keytool -genkeypair -alias certificatekey -keyalg RSA -validity 365 -keystore keystore.jks`
  
  `$ keytool -export -alias certificatekey -keystore keystore.jks -rfc -file selfsignedcert.cer`
  
  `$ keytool -import -alias certificatekey -file selfsignedcert.cer -keystore truststore.jks`
  
  [more info](http://www.eclipse.org/jetty/documentation/current/configuring-ssl.html)
  
2. Update app password `sslContextFactory.setKeyStorePassword("123456");`

3. Launch app. Open https://localhost:8082 (you may change port). Confirm that cert is trusted. Save cert than go to browser settings and add this cert to trusted.

4. try this in browser dev console `new WebSocket("wss://localhost:8082");`

5. if you have troubles uncomment `BasicConfigurator.configure();`. This should at least give some hints.

Project is using [org.eclipse.jetty.aggregate:jetty-all:9.3.9.v20160517](http://www.eclipse.org/jetty/) and [log4j](http://logging.apache.org/log4j/2.x/)
