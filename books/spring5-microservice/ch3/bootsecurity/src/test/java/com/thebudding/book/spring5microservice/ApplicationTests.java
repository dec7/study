package com.thebudding.book.spring5microservice;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thebudding.book.spring5microservice.Application.Greet;
import java.io.IOException;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class ApplicationTests {

  @Autowired private TestRestTemplate restTemplate;

  @Test
  public void testSpringBootApp() throws JsonProcessingException, IOException {
    String body = restTemplate.getForObject("/", String.class);
    assertThat(
        new ObjectMapper().readTree(body).get("message").textValue(), equalTo("Hello World!"));
  }

  @Test
  public void testOAuthService() {
    ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
    resource.setUsername("guest");
    resource.setPassword("guest123");
    resource.setAccessTokenUri("http://localhost:8080/oauth/token");
    resource.setClientId("trustedclient");
    resource.setClientSecret("trustedclient123");
    resource.setGrantType("password");
    resource.setScope(Arrays.asList(new String[] { "read", "write", "trust" }));

    DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();
    OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resource, clientContext);

    Greet greet = restTemplate.getForObject("http://localhostL8080", Greet.class);
    assertThat(greet.getMessage(), equalTo("Hello World!"));

  }
}
