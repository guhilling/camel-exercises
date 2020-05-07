package it.hilling;

import io.fabric8.kubernetes.api.model.ConfigMap;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestConfigurator extends RouteBuilder {

	@Autowired
	Environment environment;

	@Autowired
	KubernetesClient client;


	@Override
	public void configure() {
		try {
			final List<ConfigMap> configMap = client.configMaps()
													.list()
													.getItems();
			configMap.forEach(
					cm -> log.info("found cm {}", cm.getMetadata()
													.getName())
			);
		} catch (RuntimeException re) {
			log.error("cannot list configmap", re);
		}

		restConfiguration()
		.component("servlet")
		.bindingMode(RestBindingMode.json)
		.contextPath(environment.getProperty("camelrest.contextPath"))
		.port(environment.getProperty("camelrest.port"))
		.apiContextPath("/api-docs")
		.apiProperty("cors", "true")
		.apiProperty("api.title", environment.getProperty("camel.springboot.name"))
		.apiProperty("api.version", environment.getProperty("camelrest.apiversion"))
		.host(environment.getProperty("camelrest.host"))
		.dataFormatProperty("prettyPrint", "true");
	}


}
