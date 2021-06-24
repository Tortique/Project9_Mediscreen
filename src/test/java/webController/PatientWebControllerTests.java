package webController;

import com.abernathyclinic.mediscreen.MediscreenApplication;
import com.abernathyclinic.mediscreen.controller.webController.PatientWebController;
import com.abernathyclinic.mediscreen.domain.Patient;
import com.abernathyclinic.mediscreen.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PatientWebController.class)
@ContextConfiguration(classes = {MediscreenApplication.class})
public class PatientWebControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientRepository patientRepository;

    @Test
    void getAllPatientsTest() throws Exception {
        mockMvc.perform(get("/patient/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("patient/list"));
    }

    @Test
    void getAddPatient() throws Exception {
        mockMvc.perform(get("/patient/add"))
        .andExpect(status().isOk())
        .andExpect(view().name("patient/add"));
    }

    @Test
    void getAddPatientProcess() throws Exception {
        Patient patient = new Patient("lastName", "firstName", "dateOfBirth", "gender", "address", "phone");

        when(patientRepository.save(patient)).thenReturn(patient);

        mockMvc.perform(post("/patient/add").flashAttr("patient", patient))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/patient/list"));

    }

    @Test
    void getUpdatePatient() throws Exception {
        Patient patient = new Patient("lastName", "firstName", "dateOfBirth", "gender", "address", "phone");

        UUID id = UUID.randomUUID();
        when(patientRepository.findById(id)).thenReturn(Optional.of(patient));

        mockMvc.perform(get("/patient/edit/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("patient/edit"));
    }
}
