package com.example.assurance.dto;



import com.example.assurance.dto.response.AssuranceResponseDto;
import com.example.assurance.model.Assurance;

import java.util.ArrayList;
import java.util.List;

public class mapper {




    public static AssuranceResponseDto assuranceToAssuranceResponseDto(Assurance assurance) {
        AssuranceResponseDto assuranceResponseDto = new AssuranceResponseDto();
       assuranceResponseDto.setId(assurance.getId());
       assuranceResponseDto.setNomPatient(assurance.getNomPatient());
       assuranceResponseDto.setPrenomPatient(assurance.getPrenomPatient());
       assuranceResponseDto.setDatePrestation(assurance.getDatePrestation());
       assuranceResponseDto.setIdentitePatient(assurance.getIdentitePatient());
       assuranceResponseDto.setMontantConvention(assurance.getMontantConvention());
       assuranceResponseDto.setTypePrestation(assurance.getTypePrestation());
       assuranceResponseDto.setCodeFacture(assurance.getCodeFacture());
       assuranceResponseDto.setNumAffeliation(assurance.getNumAffeliation());


        return assuranceResponseDto;
    }

    public static List<AssuranceResponseDto> assurancesToAssuranceResponseDtos(List<Assurance> assurances) {
        List<AssuranceResponseDto> assuranceResponseDtos = new ArrayList<>();
        for (Assurance assurance : assurances) {
            assuranceResponseDtos.add(assuranceToAssuranceResponseDto(assurance));
        }
        return assuranceResponseDtos;
    }



}



