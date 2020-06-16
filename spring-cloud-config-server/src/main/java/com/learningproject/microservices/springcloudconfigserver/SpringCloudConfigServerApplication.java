package com.learningproject.microservices.springcloudconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class SpringCloudConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfigServerApplication.class, args);
	}

	/*
	 * {"name":"limits-service","profiles":["default"],"label":null,"version":
	 * "e718b19c2a9e5efff8b51e7d88b603495f456408","state":null,"propertySources":[{
	 * "name":
	 * "file:///C:/Softwares/eclipse_projects/MicroServices_WS/git-localconfig-repo/limits-service.properties"
	 * ,"source":{"limits-service.minimum":"8","limits-service.maximum":"888"}}]}
	 */
}
