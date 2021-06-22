package com.abernathyclinic.mediscreen.controller.webController;

import com.abernathyclinic.mediscreen.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
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
}
