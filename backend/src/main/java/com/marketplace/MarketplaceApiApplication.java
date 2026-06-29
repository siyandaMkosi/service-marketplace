package com.marketplace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class MarketplaceApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketplaceApiApplication.class, args);
    }
}
