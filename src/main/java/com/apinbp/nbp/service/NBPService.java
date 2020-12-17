package com.apinbp.nbp.service;

import com.apinbp.nbp.model.CodeCurrency;
import com.apinbp.nbp.model.ExchangeRates;
import com.apinbp.nbp.model.ExchangeRatesTable;

import java.util.List;

public interface NBPService{
    List<ExchangeRatesTable> showAllCurrency();
    ExchangeRates findByCode(String code);
    float exchangeCurrency(int amount, CodeCurrency from, CodeCurrency to);
}
