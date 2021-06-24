package com.abernathyclinic.mediscreen.repository;

import com.abernathyclinic.mediscreen.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {
    Optional<Patient> findByFirstNameAndLastName(String firstName, String lastName);
}
