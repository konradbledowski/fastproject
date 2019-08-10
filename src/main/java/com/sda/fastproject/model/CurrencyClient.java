package com.sda.fastproject.model;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
public class CurrencyClient {
    public CurrencyModel getCurrencyRates() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CurrencyModel> exchange = restTemplate.exchange("https://api.exchangeratesapi.io/latest?base=PLN",
        HttpMethod.GET,
                HttpEntity.EMPTY,
                CurrencyModel.class
        );
        return exchange.getBody();
    }

}
