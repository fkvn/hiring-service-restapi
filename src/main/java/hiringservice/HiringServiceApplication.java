package hiringservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@Slf4j
public class HiringServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(HiringServiceApplication.class, args);
    log.warn("starting log");
  }
}
