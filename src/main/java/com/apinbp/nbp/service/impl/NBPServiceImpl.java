package com.apinbp.nbp.service.impl;

import com.apinbp.nbp.model.*;
import com.apinbp.nbp.model.Currency;
import com.apinbp.nbp.repository.CrossConvertRepository;
import com.apinbp.nbp.service.NBPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class NBPServiceImpl implements NBPService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CrossConvertRepository crossConvertRepository;

    @Override
    public List<ExchangeRatesTable> showAllCurrency() {
        ResponseEntity<ExchangeRatesTable[]> entity = restTemplate.getForEntity("http://api.nbp.pl/api/exchangerates/tables/{table}/",
                ExchangeRatesTable[].class,
                "C");
        return entity.getBody() != null ? Arrays.stream(entity.getBody()).limit(5).collect(Collectors.toList()) : Collections.emptyList();
    }

    @Override
    public ExchangeRates findByCode(String code) {

        Map<String, String> map = new HashMap<>();
        map.put("table", "C");
        map.put("code", code);

        ResponseEntity<ExchangeRates> entity = restTemplate.getForEntity("http://api.nbp.pl/api/exchangerates/rates/{table}/{code}/today/",
                ExchangeRates.class,
                map);
        return entity.getBody();
    }

    @Override
    public float exchangeCurrency(int amount, CodeCurrency from, CodeCurrency to) {
        float result;

        if (from.name().equalsIgnoreCase("PL")){
            Rate rate = getRate(to.name());
            result = amount / rate.getBid();
        } else if (to.name().equalsIgnoreCase("PL")){
           Rate rate = getRate(from.name());
           result = amount * rate.getAsk();
        } else {
            result = convert(amount, from, to);
        }
        return result;
    }

    private float convert(int amount, CodeCurrency from, CodeCurrency to) {
        float result = 0;
        CrossCourse crossCourse = crossConvertRepository.findByCodeCurrency(from.name());
        Optional<Currency> currency = crossCourse.getCurrencies().stream().filter(c -> c.getCodeCurrency().equalsIgnoreCase(to.name())).findFirst();
        if (currency.isPresent()){
            result = amount * (float)currency.get().getBidCurrency();
        }
        return result;
    }

    private Rate getRate(String codeCurrency){
        ResponseEntity<ExchangeRates> rate = restTemplate.getForEntity("http://api.nbp.pl/api/exchangerates/rates/c/" + codeCurrency + "/today/",
                ExchangeRates.class);
        return Objects.requireNonNull(rate.getBody()).getRates().get(0);
    }
}
