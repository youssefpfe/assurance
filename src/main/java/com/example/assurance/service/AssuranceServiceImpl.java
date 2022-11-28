package com.example.assurance.service;


import com.example.assurance.dto.request.AssuranceRequestDto;
import com.example.assurance.dto.mapper;
import com.example.assurance.dto.response.AssuranceResponseDto;
import com.example.assurance.model.Assurance;
import com.example.assurance.repository.AssuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AssuranceServiceImpl implements AssuranceService {

    public final AssuranceRepository assuranceRepository;
@Autowired
    public AssuranceServiceImpl(AssuranceRepository assuranceRepository) {
        this.assuranceRepository = assuranceRepository;
    }

    @Override
    public Assurance getAssurance(Long assuranceId) {
        return assuranceRepository.findById(assuranceId).orElseThrow(() ->
                new IllegalArgumentException("could not find category with id: " + assuranceId));
    }

    @Override
    public AssuranceResponseDto addAssurance(AssuranceRequestDto assuranceRequestDto) {
        Assurance assurance = new Assurance();
        assurance.setNomPatient(assuranceRequestDto.getNomPatient());
        assurance.setPrenomPatient(assuranceRequestDto.getPrenomPatient());
        assurance.setCodeFacture(assuranceRequestDto.getCodeFacture());
        assurance.setDatePrestation(assuranceRequestDto.getDatePrestation());
        assurance.setIdentitePatient(assuranceRequestDto.getIdentitePatient());
        assurance.setTypePrestation(assuranceRequestDto.getTypePrestation());
        assurance.setMontantConvention(assuranceRequestDto.getMontantConvention());
        assurance.setNumAffeliation(assuranceRequestDto.getNumAffeliation());

        return mapper.assuranceToAssuranceResponseDto(assuranceRepository.save(assurance));
    }

    @Override
    public AssuranceResponseDto getAssuranceById(Long assuranceId) {
        Assurance assurance = getAssurance(assuranceId);
        return mapper.assuranceToAssuranceResponseDto(assurance);
    }

    @Override
    public List<AssuranceResponseDto> getAssurances() {
        List<Assurance> assurances = StreamSupport
                .stream(assuranceRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return mapper.assurancesToAssuranceResponseDtos(assurances);
    }

    @Override
    public AssuranceResponseDto deleteAssurance(Long assuranceId) {
        Assurance assurance = getAssurance(assuranceId);
        assuranceRepository.delete(assurance);
        return mapper.assuranceToAssuranceResponseDto(assurance);
    }
    @Override
    public String deleteAllAssurance() {
        assuranceRepository.deleteAll();

        return "Assurances effacer";
    }
@Transactional
    @Override
    public AssuranceResponseDto editAssurance(Long assuranceId, AssuranceRequestDto assuranceRequestDto) {
    Assurance assurance = getAssurance(assuranceId);
    assurance.setNomPatient(assuranceRequestDto.getNomPatient());
    assurance.setPrenomPatient(assuranceRequestDto.getPrenomPatient());
    assurance.setCodeFacture(assuranceRequestDto.getCodeFacture());
    assurance.setDatePrestation(assuranceRequestDto.getDatePrestation());
    assurance.setIdentitePatient(assuranceRequestDto.getIdentitePatient());
    assurance.setTypePrestation(assuranceRequestDto.getTypePrestation());
    assurance.setMontantConvention(assuranceRequestDto.getMontantConvention());
    assurance.setNumAffeliation(assuranceRequestDto.getNumAffeliation());


    return mapper.assuranceToAssuranceResponseDto(assurance);
    }



    @Override
    public List<AssuranceResponseDto> getAssurancesByDates(Date d1, Date d2) {
        List<Assurance> assurances = StreamSupport
                .stream(assuranceRepository.findAllAssuranceByDatePrestationBetween(d1,d2).spliterator(), false)
                .collect(Collectors.toList());


        return mapper.assurancesToAssuranceResponseDtos(assurances);
    }




}
