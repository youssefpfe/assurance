package com.example.assurance.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssuranceResponseDto {
    private Long id;
    private String nomPatient;
    private String prenomPatient;
    private Double identitePatient;
    private String codeFacture;
    private String typePrestation;
    private Double montantConvention;
    private Date datePrestation;
    private String numAffeliation;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date postedAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdatedAt;


    public AssuranceResponseDto(Long id, String nomPatient, String prenomPatient, Double identitePatient, String codeFacture, Double montantConvention, Date datePrestation, String typePrestation) {
        this.id = id;
        this.nomPatient = nomPatient;
        this.prenomPatient = prenomPatient;
        this.identitePatient = identitePatient;
        this.codeFacture = codeFacture;
        this.typePrestation = typePrestation;
        this.montantConvention = montantConvention;
        this.datePrestation = datePrestation;
    }
}
