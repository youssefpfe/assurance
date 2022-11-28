package com.example.assurance.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.assurance.dto.request.AssuranceRequestDto;
import com.example.assurance.dto.response.AssuranceResponseDto;
import com.example.assurance.model.Assurance;
import com.example.assurance.repository.AssuranceRepository;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AssuranceServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AssuranceServiceImplTest {
    @MockBean
    private AssuranceRepository assuranceRepository;

    @Autowired
    private AssuranceServiceImpl assuranceServiceImpl;

    /**
     * Method under test: {@link AssuranceServiceImpl#getAssurance(Long)}
     */
    @Test
    void testGetAssurance() {
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
        Optional<Assurance> ofResult = Optional.of(assurance);
        when(assuranceRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(assurance, assuranceServiceImpl.getAssurance(123L));
        verify(assuranceRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AssuranceServiceImpl#getAssurance(Long)}
     */
    @Test
    void testGetAssurance2() {
        when(assuranceRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> assuranceServiceImpl.getAssurance(123L));
        verify(assuranceRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AssuranceServiceImpl#getAssurance(Long)}
     */
    @Test
    void testGetAssurance3() {
        when(assuranceRepository.findById((Long) any())).thenThrow(new IllegalArgumentException());
        assertThrows(IllegalArgumentException.class, () -> assuranceServiceImpl.getAssurance(123L));
        verify(assuranceRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AssuranceServiceImpl#addAssurance(AssuranceRequestDto)}
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
        when(assuranceRepository.save((Assurance) any())).thenReturn(assurance);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        AssuranceResponseDto actualAddAssuranceResult = assuranceServiceImpl
                .addAssurance(new AssuranceRequestDto("Nom Patient", "Prenom Patient", 10.0d, "Code Facture", "Type Prestation",
                        10.0d, Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()), "Num Affeliation"));
        assertEquals("Code Facture", actualAddAssuranceResult.getCodeFacture());
        assertEquals("Type Prestation", actualAddAssuranceResult.getTypePrestation());
        assertEquals("Prenom Patient", actualAddAssuranceResult.getPrenomPatient());
        assertEquals("Num Affeliation", actualAddAssuranceResult.getNumAffeliation());
        assertEquals("Nom Patient", actualAddAssuranceResult.getNomPatient());
        assertEquals(10.0d, actualAddAssuranceResult.getMontantConvention().doubleValue());
        assertEquals(10.0d, actualAddAssuranceResult.getIdentitePatient().doubleValue());
        assertEquals(123L, actualAddAssuranceResult.getId().longValue());
        assertSame(fromResult, actualAddAssuranceResult.getDatePrestation());
        verify(assuranceRepository).save((Assurance) any());
    }


    /**
     * Method under test: {@link AssuranceServiceImpl#addAssurance(AssuranceRequestDto)}
     */
    @Test
    void testAddAssurance3() {
        when(assuranceRepository.save((Assurance) any())).thenThrow(new IllegalArgumentException());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertThrows(IllegalArgumentException.class,
                () -> assuranceServiceImpl.addAssurance(
                        new AssuranceRequestDto("Nom Patient", "Prenom Patient", 10.0d, "Code Facture", "Type Prestation", 10.0d,
                                Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()), "Num Affeliation")));
        verify(assuranceRepository).save((Assurance) any());
    }

    /**
     * Method under test: {@link AssuranceServiceImpl#getAssuranceById(Long)}
     */
    @Test
    void testGetAssuranceById() {
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
        Optional<Assurance> ofResult = Optional.of(assurance);
        when(assuranceRepository.findById((Long) any())).thenReturn(ofResult);
        AssuranceResponseDto actualAssuranceById = assuranceServiceImpl.getAssuranceById(123L);
        assertEquals("Code Facture", actualAssuranceById.getCodeFacture());
        assertEquals("Type Prestation", actualAssuranceById.getTypePrestation());
        assertEquals("Prenom Patient", actualAssuranceById.getPrenomPatient());
        assertEquals("Num Affeliation", actualAssuranceById.getNumAffeliation());
        assertEquals("Nom Patient", actualAssuranceById.getNomPatient());
        assertEquals(10.0d, actualAssuranceById.getMontantConvention().doubleValue());
        assertEquals(10.0d, actualAssuranceById.getIdentitePatient().doubleValue());
        assertEquals(123L, actualAssuranceById.getId().longValue());
        assertSame(fromResult, actualAssuranceById.getDatePrestation());
        verify(assuranceRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AssuranceServiceImpl#getAssuranceById(Long)}
     */
    @Test
    void testGetAssuranceById2() {
        when(assuranceRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> assuranceServiceImpl.getAssuranceById(123L));
        verify(assuranceRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AssuranceServiceImpl#getAssuranceById(Long)}
     */
    @Test
    void testGetAssuranceById3() {
        when(assuranceRepository.findById((Long) any())).thenThrow(new IllegalArgumentException());
        assertThrows(IllegalArgumentException.class, () -> assuranceServiceImpl.getAssuranceById(123L));
        verify(assuranceRepository).findById((Long) any());
    }



    /**
     * Method under test: {@link AssuranceServiceImpl#getAssurances()}
     */
    @Test
    void testGetAssurances3() {
        when(assuranceRepository.findAll()).thenThrow(new IllegalArgumentException());
        assertThrows(IllegalArgumentException.class, () -> assuranceServiceImpl.getAssurances());
        verify(assuranceRepository).findAll();
    }

    /**
     * Method under test: {@link AssuranceServiceImpl#deleteAssurance(Long)}
     */
    @Test
    void testDeleteAssurance() {
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
        Optional<Assurance> ofResult = Optional.of(assurance);
        doNothing().when(assuranceRepository).delete((Assurance) any());
        when(assuranceRepository.findById((Long) any())).thenReturn(ofResult);
        AssuranceResponseDto actualDeleteAssuranceResult = assuranceServiceImpl.deleteAssurance(123L);
        assertEquals("Code Facture", actualDeleteAssuranceResult.getCodeFacture());
        assertEquals("Type Prestation", actualDeleteAssuranceResult.getTypePrestation());
        assertEquals("Prenom Patient", actualDeleteAssuranceResult.getPrenomPatient());
        assertEquals("Num Affeliation", actualDeleteAssuranceResult.getNumAffeliation());
        assertEquals("Nom Patient", actualDeleteAssuranceResult.getNomPatient());
        assertEquals(10.0d, actualDeleteAssuranceResult.getMontantConvention().doubleValue());
        assertEquals(10.0d, actualDeleteAssuranceResult.getIdentitePatient().doubleValue());
        assertEquals(123L, actualDeleteAssuranceResult.getId().longValue());
        assertSame(fromResult, actualDeleteAssuranceResult.getDatePrestation());
        verify(assuranceRepository).findById((Long) any());
        verify(assuranceRepository).delete((Assurance) any());
    }

    /**
     * Method under test: {@link AssuranceServiceImpl#deleteAssurance(Long)}
     */
    @Test
    void testDeleteAssurance2() {
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
        Optional<Assurance> ofResult = Optional.of(assurance);
        doThrow(new IllegalArgumentException()).when(assuranceRepository).delete((Assurance) any());
        when(assuranceRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(IllegalArgumentException.class, () -> assuranceServiceImpl.deleteAssurance(123L));
        verify(assuranceRepository).findById((Long) any());
        verify(assuranceRepository).delete((Assurance) any());
    }

    /**
     * Method under test: {@link AssuranceServiceImpl#deleteAssurance(Long)}
     */
    @Test
    void testDeleteAssurance3() {
        doNothing().when(assuranceRepository).delete((Assurance) any());
        when(assuranceRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> assuranceServiceImpl.deleteAssurance(123L));
        verify(assuranceRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AssuranceServiceImpl#deleteAllAssurance()}
     */
    @Test
    void testDeleteAllAssurance() {
        doNothing().when(assuranceRepository).deleteAll();
        assertEquals("Assurances effacer", assuranceServiceImpl.deleteAllAssurance());
        verify(assuranceRepository).deleteAll();
    }

    /**
     * Method under test: {@link AssuranceServiceImpl#deleteAllAssurance()}
     */
    @Test
    void testDeleteAllAssurance2() {
        doThrow(new IllegalArgumentException()).when(assuranceRepository).deleteAll();
        assertThrows(IllegalArgumentException.class, () -> assuranceServiceImpl.deleteAllAssurance());
        verify(assuranceRepository).deleteAll();
    }

    /**
     * Method under test: {@link AssuranceServiceImpl#editAssurance(Long, AssuranceRequestDto)}
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
        Optional<Assurance> ofResult = Optional.of(assurance);
        when(assuranceRepository.findById((Long) any())).thenReturn(ofResult);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant());
        AssuranceResponseDto actualEditAssuranceResult = assuranceServiceImpl.editAssurance(123L,
                new AssuranceRequestDto("Nom Patient", "Prenom Patient", 10.0d, "Code Facture", "Type Prestation", 10.0d,
                        fromResult, "Num Affeliation"));
        assertEquals("Code Facture", actualEditAssuranceResult.getCodeFacture());
        assertEquals("Type Prestation", actualEditAssuranceResult.getTypePrestation());
        assertEquals("Prenom Patient", actualEditAssuranceResult.getPrenomPatient());
        assertEquals("Num Affeliation", actualEditAssuranceResult.getNumAffeliation());
        assertEquals("Nom Patient", actualEditAssuranceResult.getNomPatient());
        assertEquals(10.0d, actualEditAssuranceResult.getMontantConvention().doubleValue());
        assertEquals(10.0d, actualEditAssuranceResult.getIdentitePatient().doubleValue());
        assertEquals(123L, actualEditAssuranceResult.getId().longValue());
        assertSame(fromResult, actualEditAssuranceResult.getDatePrestation());
        verify(assuranceRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AssuranceServiceImpl#editAssurance(Long, AssuranceRequestDto)}
     */
    @Test
    void testEditAssurance2() {
        when(assuranceRepository.findById((Long) any())).thenReturn(Optional.empty());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertThrows(IllegalArgumentException.class,
                () -> assuranceServiceImpl.editAssurance(123L,
                        new AssuranceRequestDto("Nom Patient", "Prenom Patient", 10.0d, "Code Facture", "Type Prestation", 10.0d,
                                Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()), "Num Affeliation")));
        verify(assuranceRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AssuranceServiceImpl#editAssurance(Long, AssuranceRequestDto)}
     */
    @Test
    void testEditAssurance3() {
        when(assuranceRepository.findById((Long) any())).thenThrow(new IllegalArgumentException());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertThrows(IllegalArgumentException.class,
                () -> assuranceServiceImpl.editAssurance(123L,
                        new AssuranceRequestDto("Nom Patient", "Prenom Patient", 10.0d, "Code Facture", "Type Prestation", 10.0d,
                                Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()), "Num Affeliation")));
        verify(assuranceRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AssuranceServiceImpl#getAssurancesByDates(Date, Date)}
     */
    @Test
    void testGetAssurancesByDates() {
        when(assuranceRepository.findAllAssuranceByDatePrestationBetween((Date) any(), (Date) any()))
                .thenReturn(new ArrayList<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date d1 = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertTrue(assuranceServiceImpl
                .getAssurancesByDates(d1, Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()))
                .isEmpty());
        verify(assuranceRepository).findAllAssuranceByDatePrestationBetween((Date) any(), (Date) any());
    }

    /**
     * Method under test: {@link AssuranceServiceImpl#getAssurancesByDates(Date, Date)}
     */
    @Test
    void testGetAssurancesByDates2() {
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

        ArrayList<Assurance> assuranceList = new ArrayList<>();
        assuranceList.add(assurance);
        when(assuranceRepository.findAllAssuranceByDatePrestationBetween((Date) any(), (Date) any()))
                .thenReturn(assuranceList);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date d1 = Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant());
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertEquals(1,
                assuranceServiceImpl
                        .getAssurancesByDates(d1, Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()))
                        .size());
        verify(assuranceRepository).findAllAssuranceByDatePrestationBetween((Date) any(), (Date) any());
    }

    /**
     * Method under test: {@link AssuranceServiceImpl#getAssurancesByDates(Date, Date)}
     */
    @Test
    void testGetAssurancesByDates3() {
        when(assuranceRepository.findAllAssuranceByDatePrestationBetween((Date) any(), (Date) any()))
                .thenThrow(new IllegalArgumentException());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date d1 = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertThrows(IllegalArgumentException.class, () -> assuranceServiceImpl.getAssurancesByDates(d1,
                Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant())));
        verify(assuranceRepository).findAllAssuranceByDatePrestationBetween((Date) any(), (Date) any());
    }
}

