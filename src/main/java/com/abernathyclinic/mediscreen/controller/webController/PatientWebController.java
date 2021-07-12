package com.abernathyclinic.mediscreen.controller.webController;

import com.abernathyclinic.mediscreen.domain.Patient;
import com.abernathyclinic.mediscreen.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Optional;
import java.util.UUID;

@Controller
@ApiIgnore
public class PatientWebController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/patient/list")
    public ModelAndView getAllPatients() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("patients", patientRepository.findAll());
        modelAndView.setViewName("patient/list");
        return modelAndView;
    }
    @GetMapping("/patient/add")
    public ModelAndView addPatient(Model model) {
        Patient patient = new Patient();
        model.addAttribute("patient", patient);
        return new ModelAndView("patient/add");
    }

    @PostMapping({"/patient/add","/patient/edit/{id}"})
    public ModelAndView addPatientProcess(@ModelAttribute("patient") Patient patient) {
        ModelAndView modelAndView = new ModelAndView();
        patientRepository.save(patient);
        modelAndView.setViewName("redirect:/patient/list");
        return modelAndView;
    }

    @GetMapping("/patient/edit/{id}")
    public ModelAndView updatePatient(@PathVariable("id") UUID id, Model model) {
        Optional<Patient> patient = patientRepository.findById(id);
        model.addAttribute("patientInfo", patient.get());
        model.addAttribute("patient", patient.get());
        return new ModelAndView("patient/edit");






    }
}
