package org.king.test.mkovac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class King2024DevTestApplication {

  public static void main(String[] args) {
    SpringApplication.run(King2024DevTestApplication.class, args);
  }

}
