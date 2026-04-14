package mk.ukim.finki.emt.lab1_groupb_emt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class Lab1GroupBEmtApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lab1GroupBEmtApplication.class, args);
    }

}
