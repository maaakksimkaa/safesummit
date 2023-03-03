package com.ssummit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import com.ssummit.security.JwtTokenUtil;
import com.ssummit.service.userDetails.CustomUserDetailsService;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class CommonTest {
  @Autowired
  public MockMvc mvc;
  @Autowired
  private JwtTokenUtil jwtTokenUtil;
  @Autowired
  private CustomUserDetailsService userDetailsService;

  public String token = "";
  public HttpHeaders headers = new HttpHeaders();

  private String generateToken(String username) {
    return jwtTokenUtil.generateToken(userDetailsService.loadUserByUsername(username));
  }

  @BeforeAll
  public void prepare() {
    token = generateToken("admin");
    headers.add("Authorization", "Bearer " + token);
  }
}
