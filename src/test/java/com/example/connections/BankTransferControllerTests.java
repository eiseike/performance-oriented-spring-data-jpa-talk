package com.example.connections;

import com.example.AppTestConfiguration;
import com.example.RegisterBankTransferUseCase;
import com.example.domain.Amount;
import java.math.BigDecimal;
import java.util.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(AppTestConfiguration.class)
public class BankTransferControllerTests {

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private RegisterBankTransferUseCase regiser;

//  @BeforeEach
//  void setUp() {
//    regiser.execute("1",
//        "my reference",
//        "sender-id",
//        "receiver-id",
//        new Amount(BigDecimal.valueOf(10), Currency.getInstance("EUR")));
//  }

  @Test
  void testTransfer() {
    restTemplate.getForEntity("/transfer/sender-id", Void.class);
  }

}
