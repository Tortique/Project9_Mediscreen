import com.abernathyclinic.mediscreen.MediscreenApplication;
import com.abernathyclinic.mediscreen.domain.Patient;
import com.abernathyclinic.mediscreen.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {MediscreenApplication.class})
@DataJpaTest
public class PatientRepositoryTests {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void should_find_no_patients_if_repository_is_empty() {
        Iterable<Patient> patients = patientRepository.findAll();
        assertThat(patients).isEmpty();
    }

    @Test
    public void should_save_a_patient() {
        Patient patient = new Patient("Dylan", "Bob", "22/06/2021", "M", "20 rue du Bob", "666-666");
        testEntityManager.persist(patient);
        Patient patientSearch = patientRepository.findByFirstNameAndLastName("Bob", "Dylan").get(0);
        assertThat(patientSearch).hasFieldOrPropertyWithValue("lastName", "Dylan");
        assertThat(patientSearch).hasFieldOrPropertyWithValue("dateOfBirth", "22/06/2021");
        assertThat(patientSearch).hasFieldOrPropertyWithValue("address", "20 rue du Bob");
    }
}
