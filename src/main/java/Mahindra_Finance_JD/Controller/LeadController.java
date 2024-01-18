package Mahindra_Finance_JD.Controller;


import Mahindra_Finance_JD.Response.AddLeadResponce;
import Mahindra_Finance_JD.Response.LeadResponse;
import Mahindra_Finance_JD.Service.LeadService;
import Mahindra_Finance_JD.dto.LeadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lead/")
public class LeadController {

    @Autowired
    LeadService leadService;

    @PostMapping("/add")
    public AddLeadResponce addLeads(@Validated  @RequestBody LeadDto leadData){
        return  leadService.addLead(leadData);
    }

    @GetMapping("/get")
    public LeadResponse getLeadsByMobileNumber(@RequestParam String mobileNumber){
        return leadService.getLeadByMobileNumber(mobileNumber);
    }



}
