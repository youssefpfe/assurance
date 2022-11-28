package com.example.assurance.repository;

import com.example.assurance.model.Assurance;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface AssuranceRepository extends CrudRepository<Assurance,Long> {

    List<Assurance> findAllAssuranceByDatePrestationBetween(Date d1, Date d2);

}
