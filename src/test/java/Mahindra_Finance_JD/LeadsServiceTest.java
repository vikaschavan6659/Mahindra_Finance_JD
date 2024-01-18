package Mahindra_Finance_JD;

import Mahindra_Finance_JD.Model.LeadData;
import Mahindra_Finance_JD.Repository.LeadRepository;
import Mahindra_Finance_JD.Response.LeadResponse;
import Mahindra_Finance_JD.Service.LeadService;
import Mahindra_Finance_JD.dto.LeadDto;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class LeadsServiceTest {

    @Mock
    private LeadRepository leadsRepository;

    @InjectMocks
    private LeadService leadService;

    @Test
    public void testCreateLead() {
        LeadDto leadDTO = new LeadDto();
        leadDTO.setLeadId(1);
        leadDTO.setFirstName("vikas");
        leadDTO.setMiddleName("vitthal");
        leadDTO.setLastName("chavan");
        leadDTO.setMobileNumber("7083809252");
        leadDTO.setGender("Male");
        leadDTO.setDob(LocalDate.of(1998, 1, 15));
        leadDTO.setEmail("vikas.chavan@example.com");

        Mockito.when(leadsRepository.findById(any())).thenReturn(Optional.empty());
        Mockito.when(leadsRepository.save(any())).thenReturn(new LeadData(leadDTO.getLeadId(),leadDTO.getFirstName(),leadDTO.getMiddleName(),leadDTO.getLastName(),leadDTO.getMobileNumber(),leadDTO.getGender(),leadDTO.getDob(),leadDTO.getEmail()));


    }



    @Test
    public void testGetAllLeads() {
        String mobileNumber = "1234567890";
        LeadData lead1 = new LeadData();
        lead1.setLeadId(1);
        lead1.setFirstName("lara");
        lead1.setLastName("Doe");
        lead1.setMobileNumber("7894561230");
        lead1.setGender("Female");
        lead1.setDob(LocalDate.of(1990, 1, 15));
        lead1.setEmail("lara.doe@example.com");

        LeadData lead2 = new LeadData();
        lead2.setLeadId(1);
        lead2.setFirstName("anshul");
        lead2.setLastName("jubilee");
        lead2.setMobileNumber("7218515261");
        lead2.setGender("Male");
        lead2.setDob(LocalDate.of(1988, 1, 15));
        lead2.setEmail("anshul.jubil@example.com");

        List<LeadData> mockLeads = new ArrayList<>();
        mockLeads.add(lead1);
        mockLeads.add(lead2);

        Mockito.when(leadsRepository.findByMobileNumber(mobileNumber)).thenReturn(mockLeads);

        LeadResponse result = leadService.getLeadByMobileNumber(mobileNumber);

        assertEquals("success", result.getStatus());
        assertEquals(mockLeads, result.getData());
    }

    @Test
    public void testGetAllLeadsNoLeadsFound() {
        String mobileNumber = "1234567890";

        Mockito.when(leadsRepository.findByMobileNumber(mobileNumber)).thenReturn(new ArrayList<>());

        LeadResponse result = leadService.getLeadByMobileNumber(mobileNumber);

        assertEquals("error", result.getStatus());
        assertEquals("E10011", result.getErrorResponse().getCode());
        assertEquals("No Lead found with the Mobile Number " + mobileNumber, result.getErrorResponse().getMessages().get(0));
    }

}
