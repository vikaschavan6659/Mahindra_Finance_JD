package Mahindra_Finance_JD.Service;

import Mahindra_Finance_JD.Model.LeadData;
import Mahindra_Finance_JD.Repository.LeadRepository;
import Mahindra_Finance_JD.Response.AddLeadResponce;
import Mahindra_Finance_JD.Response.ErrorResponse;
import Mahindra_Finance_JD.Response.LeadResponse;
import Mahindra_Finance_JD.Validator.LeadValidationException;
import Mahindra_Finance_JD.Validator.LeadValidator;
import Mahindra_Finance_JD.dto.LeadDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeadService {
    Logger log = LoggerFactory.getLogger(LeadService.class);

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private LeadValidator leadValidator;

    public AddLeadResponce addLead (LeadDto leadDto){

        log.info("Create lead service ");
        Errors errors = new BeanPropertyBindingResult(leadDto, "leadDTO");
        leadValidator.validate(leadDto, errors);
        log.info("Validation PASS");

        if (errors.hasErrors()) {
            throw new LeadValidationException(errors.getAllErrors().get(0).getDefaultMessage());
        }
        Optional<LeadData> existingLead = leadRepository.findById(leadDto.getLeadId());

        if (existingLead.isPresent()) {
            Integer leadId =existingLead.get().getLeadId();
            log.info("Lead Already Exists in the database with the lead id {} ", leadId);
            List<String> errorMessage= new ArrayList<>();
            errorMessage.add("Lead Already Exists in the database with the lead id "+ leadId);
            return new AddLeadResponce("error",new ErrorResponse("E10010",errorMessage));
        }
        LeadData leadData= new LeadData(
                leadDto.getLeadId(),
                leadDto.getFirstName(),
                leadDto.getMiddleName(),
                leadDto.getLastName(),
                leadDto.getMobileNumber(),
                leadDto.getGender(),
                leadDto.getDob(),
                leadDto.getEmail()
        );
        leadRepository.save(leadData);
        return new AddLeadResponce("success","Created Leads Successfully");

    }

    public LeadResponse getLeadByMobileNumber(String mobileNumber) {
        log.info("Getting lead for mobile number {}",mobileNumber);
        List<LeadData> leads =leadRepository.findByMobileNumber(mobileNumber);

        if (leads.size()==0){
            log.info("Lead not found with mobile  number {}", mobileNumber);
            return getResponse(mobileNumber);
        }
        LeadResponse getAllLeadsResponse = new LeadResponse();
        getAllLeadsResponse.setData(leads);
        getAllLeadsResponse.setStatus("success");
        return getAllLeadsResponse;
    }

    private static LeadResponse getResponse(String mobileNumber) {
        LeadResponse getAllLeadsResponse= new LeadResponse();
        getAllLeadsResponse.setStatus("error");
        ErrorResponse errorResponse = getErrorResponse(mobileNumber);
        getAllLeadsResponse.setErrorResponse(errorResponse);
        return getAllLeadsResponse;
    }

    private static ErrorResponse getErrorResponse(String mobileNumber) {
        List<String> errorMessage= new ArrayList<>();
        errorMessage.add("No Lead found with the Mobile Number "+mobileNumber );
        ErrorResponse errorResponse= new ErrorResponse();
        errorResponse.setMessages(errorMessage);
        errorResponse.setCode("E10011");
        return errorResponse;
    }
}
