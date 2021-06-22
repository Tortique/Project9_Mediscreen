package com.abernathyclinic.mediscreen.controller;

import com.abernathyclinic.mediscreen.domain.Patient;
import com.abernathyclinic.mediscreen.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping("/addPatient")
    public @ResponseBody
    String addNewPatient(@RequestParam String lastName, @RequestParam String firstName,
                         @RequestParam String dateOfBirth, @RequestParam String gender,
                         @RequestParam String address, @RequestParam String phone) {
        Patient patient = new Patient();
        patient.setLastName(lastName);
        patient.setFirstName(firstName);
        patient.setDateOfBirth(dateOfBirth);
        patient.setGender(gender);
        patient.setAddress(address);
        patient.setPhone(phone);
        patientRepository.save(patient);
        return "Saved";
    }

    @GetMapping("/getAll")
    public @ResponseBody
    Iterable<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
}
