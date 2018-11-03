package com.thebudding.book.spring5microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @RestController
  class GreetingController {
    @RequestMapping("/")
    Mono<Greet> greet() {
      return Mono.just(new Greet("Hello World!"));
    }
  }

  static class Greet {
    private String message;
    public Greet() {}
    public Greet(String message) {
      this.message = message;
    }

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }
  }
}
