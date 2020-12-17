package com.apinbp.nbp.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rate {

    @ApiModelProperty(notes = "Numer tabeli")
    private String no;

    @ApiModelProperty(notes = "Data publikacji")
    private Date effectiveDate;

    @ApiModelProperty(notes = "Przeliczony kurs kupna waluty")
    private float bid;

    @ApiModelProperty(notes = "Przeliczony kurs sprzedaży waluty")
    private float ask;
}
