package com.abernathyclinic.mediscreen.repository;

import com.abernathyclinic.mediscreen.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
    @Override
    Optional<Patient> findById(UUID uuid);

    List<Patient> findByFirstNameAndLastName(String firstName, String lastName);
}
