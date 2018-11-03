package com.thebudding.book.spring5microservice;

import java.util.Calendar;
import java.util.concurrent.atomic.LongAdder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}

class TPSCounter {
  LongAdder count;
  int threshold = 2;
  Calendar expiry = null;

  TPSCounter() {
    count = new LongAdder();
    expiry = Calendar.getInstance();
    expiry.add(Calendar.MINUTE, 1);
  }

  boolean isExpired() {
    return Calendar.getInstance().after(expiry);
  }

  boolean isWeak() {
    return (count.intValue() > threshold);
  }

  void increment() {
    count.increment();
  }
}

@Component
class TPSHealth implements HealthIndicator {
  TPSCounter counter;

  @Override
  public Health health() {
    boolean health = howGoodIsHealth(); // perform some specific health check
    if (health) {
      return Health.outOfService().withDetail("Too many requests", "OutofService").build();
    }
    return Health.up().build();
  }

  void updateTx() {
    if (counter == null || counter.isExpired()) {
      counter = new TPSCounter();
    }
    counter.increment();
  }

  boolean howGoodIsHealth() {
    return counter != null && counter.isWeak();
  }
}

@RestController
class GreetingController {
  private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

  TPSHealth health;

  @Autowired
  GreetingController(TPSHealth health) {
    this.health = health;
  }

  @CrossOrigin
  @RequestMapping("/")
  Greet greet() {
    logger.info("Serving Request....!!!");
    health.updateTx();
    return new Greet("Hello World!");
  }
}

class Greet {
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

  public String toString() {
    return message;
  }
}
