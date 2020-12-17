package com.apinbp.nbp.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRates {

    @ApiModelProperty(notes = "Nazwa waluty")
    private String currency;

    @ApiModelProperty(notes = "Trzyliterowy kod waluty (standard ISO 4217)")
    private String code;

    @ApiModelProperty(notes = "Lista kursów poszczególnych walut w tabeli")
    private List<Rate> rates;
}
