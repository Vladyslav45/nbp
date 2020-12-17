package com.apinbp.nbp.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Currency {

    @ApiModelProperty(notes = "Trzyliterowy kod waluty (standard ISO 4217)")
    private String codeCurrency;

    @ApiModelProperty(notes = "Przeliczony kurs kupna waluty")
    private double bidCurrency;
}
