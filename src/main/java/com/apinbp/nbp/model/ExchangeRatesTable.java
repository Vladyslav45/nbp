package com.apinbp.nbp.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRatesTable {
    @ApiModelProperty(notes = "Numer tabeli")
    private String no;

    @ApiModelProperty(notes = "Data notowania")
    private Date tradingDate;

    @ApiModelProperty(notes = "Data publikacji")
    private Date effectiveDate;

    @ApiModelProperty(notes = "Lista kursów poszczególnych walut w tabeli")
    private List<Rates> rates;
}
