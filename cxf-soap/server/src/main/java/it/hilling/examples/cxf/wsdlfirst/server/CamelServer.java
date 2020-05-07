package it.hilling.examples.cxf.wsdlfirst.server;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A spring-boot application that includes a Camel route builder to setup the Camel routes
 */
@SpringBootApplication
public class CamelServer extends RouteBuilder {

    public static void main(String[] args) {
        SpringApplication.run(CamelServer.class, args);
    }

    @Override
    public void configure() {
        from("cxf:bean:customerServiceEndpoint")
       .to("CustomerServiceProcessor");
    }
}
