package com.example.assurance.service;


import com.example.assurance.dto.request.AssuranceRequestDto;
import com.example.assurance.dto.response.AssuranceResponseDto;
import com.example.assurance.model.Assurance;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface AssuranceService {
    public Assurance getAssurance(Long assuranceId);
    public AssuranceResponseDto addAssurance(AssuranceRequestDto assuranceRequestDto);
    public AssuranceResponseDto getAssuranceById(Long assuranceId);
    public List<AssuranceResponseDto> getAssurances();
    public List<AssuranceResponseDto> getAssurancesByDates(Date d1, Date d2);
    public AssuranceResponseDto deleteAssurance(Long assuranceId);
    public AssuranceResponseDto editAssurance(Long assuranceId, AssuranceRequestDto assuranceRequestDto);
    public String deleteAllAssurance();
}
