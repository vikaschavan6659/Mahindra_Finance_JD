package Mahindra_Finance_JD.Response;

import Mahindra_Finance_JD.Model.LeadData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LeadResponse {

    private String status;

    public List<LeadData> data;

    public ErrorResponse errorResponse;

    public List<LeadData> getData() {
        return data;
    }

    public void setData(List<LeadData> data) {
        this.data = data;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public LeadResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
