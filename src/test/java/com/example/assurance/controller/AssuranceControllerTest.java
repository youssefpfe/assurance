package com.example.assurance.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.assurance.dto.request.AssuranceRequestDto;
import com.example.assurance.dto.response.AssuranceResponseDto;
import com.example.assurance.model.Assurance;
import com.example.assurance.repository.AssuranceRepository;
import com.example.assurance.service.AssuranceService;
import com.example.assurance.service.AssuranceServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AssuranceController.class})
@ExtendWith(SpringExtension.class)
class AssuranceControllerTest {
    @Autowired
    private AssuranceController assuranceController;

    @MockBean
    private AssuranceService assuranceService;

    /**
     * Method under test: {@link AssuranceController#addAssurance(AssuranceRequestDto)}
     */
    @Test
    void testAddAssurance() {

        Assurance assurance = new Assurance();
        assurance.setCodeFacture("Code Facture");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        assurance.setDatePrestation(fromResult);
        assurance.setId(123L);
        assurance.setIdentitePatient(10.0d);
        assurance.setMontantConvention(10.0d);
        assurance.setNomPatient("Nom Patient");
        assurance.setNumAffeliation("Num Affeliation");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setPrenomPatient("Prenom Patient");
        assurance.setTypePrestation("Type Prestation");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        AssuranceRepository assuranceRepository = mock(AssuranceRepository.class);
        when(assuranceRepository.save((Assurance) any())).thenReturn(assurance);
        AssuranceController assuranceController = new AssuranceController(new AssuranceServiceImpl(assuranceRepository));
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        ResponseEntity<AssuranceResponseDto> actualAddAssuranceResult = assuranceController
                .addAssurance(new AssuranceRequestDto("Nom Patient", "Prenom Patient", 10.0d, "Code Facture", "Type Prestation",
                        10.0d, Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()), "Num Affeliation"));
        assertTrue(actualAddAssuranceResult.hasBody());
        assertTrue(actualAddAssuranceResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.CREATED, actualAddAssuranceResult.getStatusCode());
        AssuranceResponseDto body = actualAddAssuranceResult.getBody();
        assertEquals("Num Affeliation", body.getNumAffeliation());
        assertEquals("Nom Patient", body.getNomPatient());
        assertEquals(10.0d, body.getMontantConvention().doubleValue());
        assertEquals(10.0d, body.getIdentitePatient().doubleValue());
        assertEquals(123L, body.getId().longValue());
        assertSame(fromResult, body.getDatePrestation());
        assertEquals("Code Facture", body.getCodeFacture());
        assertEquals("Type Prestation", body.getTypePrestation());
        assertEquals("Prenom Patient", body.getPrenomPatient());
        verify(assuranceRepository).save((Assurance) any());
    }



    /**
     * Method under test: {@link AssuranceController#getAssurances()}
     */
    @Test
    void testGetAssurances() throws Exception {
        when(assuranceService.getAssurances()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/assurance/getAll");
        MockMvcBuilders.standaloneSetup(assuranceController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AssuranceController#getAssurances()}
     */
    @Test
    void testGetAssurances2() throws Exception {
        when(assuranceService.getAssurances()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/assurance/getAll");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(assuranceController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AssuranceController#deleteAssurance(Long)}
     */
    @Test
    void testDeleteAssurance() throws Exception {
        when(assuranceService.deleteAssurance((Long) any())).thenReturn(new AssuranceResponseDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/assurance/delete/{id}", 123L);
        MockMvcBuilders.standaloneSetup(assuranceController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"nomPatient\":null,\"prenomPatient\":null,\"identitePatient\":null,\"codeFacture\":null,\"typePrestation"
                                        + "\":null,\"montantConvention\":null,\"datePrestation\":null,\"numAffeliation\":null,\"postedAt\":null,\"lastUpdatedAt"
                                        + "\":null}"));
    }

    /**
     * Method under test: {@link AssuranceController#deleteAllAssurance()}
     */
    @Test
    void testDeleteAllAssurance() throws Exception {
        when(assuranceService.deleteAllAssurance()).thenReturn("Delete All Assurance");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/assurance/deleteAll");
        MockMvcBuilders.standaloneSetup(assuranceController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Delete All Assurance"));
    }

    /**
     * Method under test: {@link AssuranceController#editAssurance(AssuranceRequestDto, Long)}
     */
    @Test
    void testEditAssurance() {


        Assurance assurance = new Assurance();
        assurance.setCodeFacture("Code Facture");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setDatePrestation(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setId(123L);
        assurance.setIdentitePatient(10.0d);
        assurance.setMontantConvention(10.0d);
        assurance.setNomPatient("Nom Patient");
        assurance.setNumAffeliation("Num Affeliation");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setPrenomPatient("Prenom Patient");
        assurance.setTypePrestation("Type Prestation");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        AssuranceRepository assuranceRepository = mock(AssuranceRepository.class);
        when(assuranceRepository.findById((Long) any())).thenReturn(Optional.of(assurance));
        AssuranceController assuranceController = new AssuranceController(new AssuranceServiceImpl(assuranceRepository));
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant());
        ResponseEntity<AssuranceResponseDto> actualEditAssuranceResult = assuranceController
                .editAssurance(new AssuranceRequestDto("Nom Patient", "Prenom Patient", 10.0d, "Code Facture",
                        "Type Prestation", 10.0d, fromResult, "Num Affeliation"), 123L);
        assertTrue(actualEditAssuranceResult.hasBody());
        assertTrue(actualEditAssuranceResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualEditAssuranceResult.getStatusCode());
        AssuranceResponseDto body = actualEditAssuranceResult.getBody();
        assertEquals("Num Affeliation", body.getNumAffeliation());
        assertEquals("Nom Patient", body.getNomPatient());
        assertEquals(10.0d, body.getMontantConvention().doubleValue());
        assertEquals(10.0d, body.getIdentitePatient().doubleValue());
        assertEquals(123L, body.getId().longValue());
        assertSame(fromResult, body.getDatePrestation());
        assertEquals("Code Facture", body.getCodeFacture());
        assertEquals("Type Prestation", body.getTypePrestation());
        assertEquals("Prenom Patient", body.getPrenomPatient());
        verify(assuranceRepository).findById((Long) any());
    }



    /**
     * Method under test: {@link AssuranceController#getAssurance(Long)}
     */
    @Test
    void testGetAssurance() throws Exception {
        when(assuranceService.getAssuranceById((Long) any())).thenReturn(new AssuranceResponseDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/assurance/get/{id}", 123L);
        MockMvcBuilders.standaloneSetup(assuranceController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"nomPatient\":null,\"prenomPatient\":null,\"identitePatient\":null,\"codeFacture\":null,\"typePrestation"
                                        + "\":null,\"montantConvention\":null,\"datePrestation\":null,\"numAffeliation\":null,\"postedAt\":null,\"lastUpdatedAt"
                                        + "\":null}"));
    }

    /**
     * Method under test: {@link AssuranceController#getConsultationsByDates(Date, Date)}
     */
    @Test
    void testGetConsultationsByDates() throws Exception {
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/assurance/getByDates/{d1}/{d2}",
                fromResult, Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(assuranceController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}

