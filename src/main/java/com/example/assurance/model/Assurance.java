package com.example.assurance.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "assurance")
public class Assurance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nomPatient;
    private String prenomPatient;
    private Double identitePatient;
    private String typePrestation;
    private Double montantConvention;
    private Date datePrestation;
    private String codeFacture;
    private String numAffeliation;
    @CreationTimestamp
    private Date postedAt;
    @UpdateTimestamp
    private Date updatedAt ;

    public Assurance(String nomPatient, String prenomPatient, Double identitePatient, String typePrestation, Double montantConvention, Date datePrestation, String codeFacture) {
        this.nomPatient = nomPatient;
        this.prenomPatient = prenomPatient;
        this.identitePatient = identitePatient;
        this.typePrestation = typePrestation;
        this.montantConvention = montantConvention;
        this.datePrestation = datePrestation;
        this.codeFacture = codeFacture;
    }

    public Assurance(Long id, String nomPatient, String prenomPatient, Double identitePatient, String typePrestation, Double montantConvention, Date datePrestation, String codeFacture) {
        this.id = id;
        this.nomPatient = nomPatient;
        this.prenomPatient = prenomPatient;
        this.identitePatient = identitePatient;
        this.typePrestation = typePrestation;
        this.montantConvention = montantConvention;
        this.datePrestation = datePrestation;
        this.codeFacture = codeFacture;
    }
}
