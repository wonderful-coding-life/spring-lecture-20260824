package com.example.lombok;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TestApplication implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
//        Product product = new Product();
//        product.setName("애플와치");
//        product.setDescription("애플이 만든 스마트와치");
//        product.setPrice(1200000);

        Product product = Product.builder().name("삼성갤럭시").description("삼성이 만든 스마트 와치").build();


        log.trace("Entering calculatePrice()");
        log.debug("User ID: 123");
        log.info("Application started - {}", product);
        log.warn("Disk space is low");
        log.error("Failed to connect to database");
    }
}
