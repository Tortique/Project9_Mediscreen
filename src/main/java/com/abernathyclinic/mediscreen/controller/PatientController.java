package com.abernathyclinic.mediscreen.controller;

import com.abernathyclinic.mediscreen.domain.Patient;
import com.abernathyclinic.mediscreen.exception.PatientNotFoundException;
import com.abernathyclinic.mediscreen.repository.PatientRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @ApiOperation(value = "ADD patient")
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

    @ApiOperation(value = "GET all Patients in a list")
    @GetMapping("/getAll")
    public @ResponseBody
    Iterable<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @ApiOperation(value = "GET a Patient by his Id")
    @GetMapping("/getById")
    public Patient getPatientById(@RequestParam UUID id) throws PatientNotFoundException {
        return patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient with ID : " + id + "could not be in the database"));
    }

    @ApiOperation(value = "GET a Patient by his FirstName and LastName")
    @GetMapping("/getByFirstNameAndLastName")
    public Patient getPatientByName(@RequestParam String firstName, @RequestParam String lastName) throws PatientNotFoundException{
        return patientRepository.findByFirstNameAndLastName(firstName, lastName).orElseThrow(() -> new PatientNotFoundException("Patient with name : " + firstName + " " + lastName + " could not be in the database"));
    }

    @ApiOperation(value = "PUT patient's info")
    @PutMapping("/updatePatient/{id}")
    public @ResponseBody
    String updatePatient(@PathVariable("id") UUID id,@RequestBody Patient patient) throws PatientNotFoundException{
        Patient patientToUpdate = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient with ID : " + id + " could not be in the database"));
        patientToUpdate.setFirstName(patient.getFirstName());
        patientToUpdate.setLastName(patient.getLastName());
        patientToUpdate.setDateOfBirth(patient.getDateOfBirth());
        patientToUpdate.setGender(patient.getGender());
        patientRepository.save(patientToUpdate);
        return "Patient updated";
    }
}
