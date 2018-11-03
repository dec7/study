package com.thebudding.book.spring5microservice;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

  @Autowired private TestRestTemplate restTemplate;

  @Test
  public void testSpringBootApp() throws JsonProcessingException, IOException {
    String body = restTemplate.getForObject("/", String.class);
    assertThat(
        new ObjectMapper().readTree(body).get("message").textValue(), equalTo("Hello World!"));
  }
}
