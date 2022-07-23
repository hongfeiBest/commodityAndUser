package cn.hongfei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CommodityApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommodityApplication.class,args);
    }
}
