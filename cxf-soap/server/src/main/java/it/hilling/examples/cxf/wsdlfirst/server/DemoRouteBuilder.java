package it.hilling.examples.cxf.wsdlfirst.server;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class DemoRouteBuilder extends RouteBuilder {
    @Override
    public void configure() {
        from("cxf:bean:customerServiceEndpoint")
                .to("CustomerServiceProcessor");
    }

}
