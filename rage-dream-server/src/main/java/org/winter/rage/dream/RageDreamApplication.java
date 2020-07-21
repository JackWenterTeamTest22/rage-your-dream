package org.winter.rage.dream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

/**
 * @ClassName: RageDreamApplication
 * @Description:
 * @Author wented
 * @Date 2020/7/21 3:28 PM
 **/
@SpringBootApplication(scanBasePackages = {"org.winter.rage.dream"})
@ImportResource(locations={""})
@Slf4j
public class RageDreamApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(RageDreamApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RageDreamApplication.class);
    }

}
