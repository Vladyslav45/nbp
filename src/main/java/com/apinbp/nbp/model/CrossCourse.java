package com.apinbp.nbp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "cross_course")
public class CrossCourse {

    @Id
    @JsonIgnore
    @ApiModelProperty(notes = "Identyfikator w bazie danych")
    private String id;

    @Field(name = "name_currency")
    @ApiModelProperty(notes = "Nazwa waluty")
    private String nameCurrency;

    @Field(name = "code_currency")
    @ApiModelProperty(notes = "Trzyliterowy kod waluty (standard ISO 4217)")
    private String codeCurrency;

    @Field(name = "list_cross_course")
    @ApiModelProperty(notes = "Lista walut dostepnych do przeliczenie")
    private List<Currency> currencies;
}
