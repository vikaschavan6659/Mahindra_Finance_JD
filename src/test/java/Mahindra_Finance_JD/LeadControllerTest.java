package Mahindra_Finance_JD;

import Mahindra_Finance_JD.Controller.LeadController;
import Mahindra_Finance_JD.Response.AddLeadResponce;
import Mahindra_Finance_JD.Response.LeadResponse;
import Mahindra_Finance_JD.Service.LeadService;
import Mahindra_Finance_JD.dto.LeadDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class LeadControllerTest {

    @Mock
    private LeadService leadService;

    @InjectMocks
    private LeadController leadController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddLeads() {

        LeadDto leadDTO = new LeadDto();
        leadDTO.setLeadId(1);
        leadDTO.setFirstName("shri");
        leadDTO.setLastName("ram");
        leadDTO.setMobileNumber("8237999001");
        leadDTO.setGender("Male");
        leadDTO.setDob(LocalDate.of(1990, 1, 15));
        leadDTO.setEmail("shri.ram@example.com");
        AddLeadResponce expectedApiResponse = new AddLeadResponce("success", "Created Leads Successfully");

        when(leadService.addLead(any())).thenReturn(expectedApiResponse);

        AddLeadResponce actualApiResponse = leadController.addLeads(leadDTO);

        Mockito.verify(leadService).addLead(leadDTO);

        assertEquals(expectedApiResponse, actualApiResponse);
    }

    @Test
    public void testGetAllLeads() {
        String mobileNumber = "1234567890";
        LeadResponse expectedResponse = new LeadResponse();

        when(leadService.getLeadByMobileNumber(mobileNumber)).thenReturn(expectedResponse);

        LeadResponse actualResponse = leadController.getLeadsByMobileNumber(mobileNumber);

        Mockito.verify(leadService).getLeadByMobileNumber(mobileNumber);

        assertEquals(expectedResponse, actualResponse);
    }
}
