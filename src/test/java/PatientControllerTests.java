import com.abernathyclinic.mediscreen.MediscreenApplication;
import com.abernathyclinic.mediscreen.controller.PatientController;
import com.abernathyclinic.mediscreen.domain.Patient;
import com.abernathyclinic.mediscreen.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(PatientController.class)
@ContextConfiguration(classes = {MediscreenApplication.class})
public class PatientControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientRepository patientRepository;

    @Test
    void addPatientTest() throws Exception {
        Patient patientSaved = new Patient("lastName", "firstName", "dateOfBirth", "gender", "address", "phone");

        when(patientRepository.save(patientSaved)).thenReturn(patientSaved);

        MvcResult mvcResult = mockMvc.perform(post("/api/patient/addPatient?lastName=lastName&firstName=firstName&dateOfBirth=dateOfBirth&gender=gender&address=address&phone=phone"))
                .andDo(print()).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        assertEquals(200, response.getStatus());
        assertEquals("Saved", response.getContentAsString());
    }

    @Test
    void getAllPatientTest() throws Exception {
        when(patientRepository.findAll()).thenReturn(new ArrayList<>());

        MvcResult mvcResult = mockMvc.perform(get("/api/patient/getAll")).andDo(print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(200, status);
    }

    @Test
    void getPatientByIdTest() throws Exception {
        when(patientRepository.findById(any(UUID.class))).thenReturn(Optional.of(new Patient()));

        MvcResult mvcResult = mockMvc.perform(get("/api/patient/getById?id=b42a8ef5-8baa-4bc2-89aa-d18cdc3239f9")).andDo(print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(200, status);
        verify(patientRepository, times(1)).findById(any(UUID.class));
    }

    @Test
    void getPatientByIdThrownNotFoundExceptionTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/patient/getById?id=b42a8ef5-8baa-4bc2-89aa-d18cdc3239f8")).andDo(print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(404, status);
    }

    @Test
    void getPatientByFirstNameAndLastNameTest() throws Exception {
        when(patientRepository.findByFirstNameAndLastName(any(String.class), any(String.class))).thenReturn(Optional.of(new Patient()));

        MvcResult mvcResult = mockMvc.perform(get("/api/patient/getByFirstNameAndLastName?firstName=firstName&lastName=lastName"))
                .andDo(print()).andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(200,status);
        verify(patientRepository, times(1)).findByFirstNameAndLastName(any(String.class), any(String.class));
    }

    @Test
    void getPatientByFirstNameAndLastNameThrownNotFoundExceptionTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/patient/getByFirstNameAndLastName?firstName=firstName&lastName=lastName\"")).andDo(print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(404, status);
    }

    @Test
    void updatePatientTest() throws Exception {
        Patient patientToUpdate = new Patient("lastName", "firstName", "dateOfBirth", "gender", "address", "phone");
        UUID uuid = UUID.randomUUID();
        when(patientRepository.findById(uuid)).thenReturn(Optional.of(patientToUpdate));
        when(patientRepository.save(patientToUpdate)).thenReturn(patientToUpdate);

        MvcResult mvcResult = mockMvc.perform(put("/api/patient/updatePatient/" + uuid).contentType(MediaType.APPLICATION_JSON).content(
                "{\"lastName\": \"lastName\",\"firstName\": \"firstName\",\"dateOfBirth\": \"dateOfBirth\",\"gender\": \"gender\"}"))
                .andDo(print()).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        assertEquals(200, response.getStatus());
        assertEquals("Patient updated", response.getContentAsString());
    }

    @Test
    void updatePatientButThrownNotFoundExceptionTest() throws Exception {
        UUID uuid = UUID.randomUUID();
        MvcResult mvcResult = mockMvc.perform(put("/api/patient/updatePatient/" + uuid).contentType(MediaType.APPLICATION_JSON).content(
                "{\"lastName\": \"lastName\",\"firstName\": \"firstName\",\"dateOfBirth\": \"dateOfBirth\",\"gender\": \"gender\"}"))
                .andDo(print()).andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(404, status);
    }

}
