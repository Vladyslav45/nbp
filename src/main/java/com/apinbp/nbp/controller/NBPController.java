package com.apinbp.nbp.controller;

import com.apinbp.nbp.model.CodeCurrency;
import com.apinbp.nbp.model.ExchangeRates;
import com.apinbp.nbp.model.ExchangeRatesTable;
import com.apinbp.nbp.service.NBPService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/exchangeRate")
@Api(description = "Udostępnienie aktualnych kursów walut pobranych z NBP API")
public class NBPController {

    @Autowired
    private NBPService nbpService;

    @GetMapping(value = "/getAllCurrency")
    @ApiOperation(value = "Udostępnienie aktualnych kursów dla listy walut")
    public List<ExchangeRatesTable> getAll(){
        return nbpService.showAllCurrency();
    }

    @GetMapping(value = "/getParticularCurrency")
    @ApiOperation(value = "Udostępnienie kursu pojedynczej waluty")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Jest w porzadku. Zwraca kurs waluty"),
            @ApiResponse(code = 400, message = "W przypadku zadania nieprawidłowo sformułowanych zapytań"),
            @ApiResponse(code = 400, message = "W przypadku zapytania obejmującego więcej niż trzy miesiące"),
            @ApiResponse(code = 404, message = "W przypadku braku danych dla prawidłowo określonego zakresu czasowego")
    })
    public ExchangeRates get(@RequestParam String code){
        return nbpService.findByCode(code);
    }

    @GetMapping(value = "/convert")
    @ApiOperation(value = "Przeliczenie na podstawie kursu wymiany dla następujących parametrów: kwota(a,ount), z jakiej waluty(from), do\n" +
            "jakiej walut(to)")
    public float exchangeCurrency(@RequestParam int amount, @RequestParam CodeCurrency from, @RequestParam CodeCurrency to){
        return nbpService.exchangeCurrency(amount, from, to);
    }

    @GetMapping(value = "/getCurrenciesForConvert")
    @ApiModelProperty(value = "listę dostępnych walut na których można wykonać przeliczenia")
    public CodeCurrency[] getCurrenciesForConvert(){
        return CodeCurrency.values();
    }
}
