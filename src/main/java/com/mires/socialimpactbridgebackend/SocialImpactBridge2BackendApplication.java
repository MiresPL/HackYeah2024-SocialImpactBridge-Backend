package com.mires.socialimpactbridgebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.mires.common.objects")
public class SocialImpactBridge2BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialImpactBridge2BackendApplication.class, args);
    }

}
