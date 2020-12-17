package com.apinbp.nbp.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rates {
    @ApiModelProperty(notes = "Nazwa waluty")
    private String currency;

    @ApiModelProperty(notes = "Trzyliterowy kod waluty (standard ISO 4217)")
    private String code;

    @ApiModelProperty(notes = "Przeliczony kurs kupna waluty")
    private float bid;

    @ApiModelProperty(notes = "Przeliczony kurs sprzedaży waluty")
    private float ask;
}
