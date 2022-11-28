package com.example.assurance.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssuranceRequestDto {

    private String nomPatient;
    private String prenomPatient;
    private Double identitePatient;
    private String codeFacture;
    private String typePrestation;
    private Double montantConvention;
    private Date datePrestation;
    private String numAffeliation;


}
