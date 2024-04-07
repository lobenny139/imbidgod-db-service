package com.imbidgo.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@ComponentScan(basePackages = {
        //imbidgod-db-service
        "com.imbidgo.db.service.provider",
        "com.imbidgod.asyncService",
        //imbidgo-redis-service
//        "com.imbidgo.cache.service.provider"
})

@Import({
        //imbidgod-db-service
        com.imbidgod.db.beanServiceConfig.ServiceAccessConfig.class,
        //imbidgo-redis-service
//        com.imbidgod.redis.beanServiceConfig.ServiceAccessConfig.class
})

@EnableJpaRepositories(basePackages = {
        //imbidgod-db-service
        "com.imbidgod.db.repository"
})

@EntityScan(basePackages = {
        // @imbidgo-db-entity
        "com.imbidgod.db.entity"
})

@SpringBootApplication
@EnableAsync
public class TestApplication {
    public static void main(String[] args) {
            SpringApplication.run(TestApplication.class, args);
    }
}
