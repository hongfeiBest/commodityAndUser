package cn.hongfei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UserApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(UserApplication.class,args);
        }catch (Throwable  e){
            System.out.println(e);
            System.out.println("-------------------");
            e.printStackTrace();
        }

    }
}
