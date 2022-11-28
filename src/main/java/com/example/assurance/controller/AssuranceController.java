package com.example.assurance.controller;


import com.example.assurance.dto.request.AssuranceRequestDto;
import com.example.assurance.dto.response.AssuranceResponseDto;
import com.example.assurance.service.AssuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
//@CrossOrigin(origins = "${baseUrl}",allowCredentials ="true",allowedHeaders = "*")
@RequestMapping("/assurance")
public class AssuranceController {

    private final AssuranceService assuranceService;
@Autowired
    public AssuranceController(AssuranceService assuranceService) {
    this.assuranceService = assuranceService;

    }

    @PostMapping("/add")
    public ResponseEntity<AssuranceResponseDto> addAssurance(
            @RequestBody final AssuranceRequestDto assuranceRequestDto) {
        AssuranceResponseDto assuranceResponseDto = assuranceService.addAssurance(assuranceRequestDto);
        return new ResponseEntity<>(assuranceResponseDto, HttpStatus.CREATED);}

    @GetMapping("/get/{id}")
    public ResponseEntity<AssuranceResponseDto> getAssurance(@PathVariable final Long id) {
       AssuranceResponseDto assuranceResponseDto= assuranceService.getAssuranceById(id);
        return new ResponseEntity<>(assuranceResponseDto, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<AssuranceResponseDto>> getAssurances() {
        List<AssuranceResponseDto> assuranceResponseDtos = assuranceService.getAssurances();
        return new ResponseEntity<>(assuranceResponseDtos, HttpStatus.OK);
    }

    @GetMapping("/getByDates/{d1}/{d2}")
    public ResponseEntity<List<AssuranceResponseDto>> getConsultationsByDates(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date d1, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable Date d2) {
        List<AssuranceResponseDto> assuranceResponseDtos = assuranceService.getAssurancesByDates(d1,d2);
        return new ResponseEntity<>(assuranceResponseDtos, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AssuranceResponseDto> deleteAssurance(@PathVariable final Long id) {
        AssuranceResponseDto assuranceResponseDto = assuranceService.deleteAssurance(id);
        return new ResponseEntity<>(assuranceResponseDto, HttpStatus.OK);


    }
     @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllAssurance() {

        return new ResponseEntity<>(assuranceService.deleteAllAssurance(), HttpStatus.OK);


    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<AssuranceResponseDto> editAssurance(
            @RequestBody final AssuranceRequestDto assuranceRequestDto,
            @PathVariable final Long id) {
        AssuranceResponseDto assuranceResponseDto = assuranceService.editAssurance(id, assuranceRequestDto);
        return new ResponseEntity<>(assuranceResponseDto, HttpStatus.OK);
    }

}
