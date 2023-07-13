package com.architecturelogicielle.webapp.actualite;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;


@Configuration
@ConfigurationProperties(prefix = "com.architecturelogicielle.webapp.actualite")
@Data
public class CustomProperties {

    private String apiUrl;

}
