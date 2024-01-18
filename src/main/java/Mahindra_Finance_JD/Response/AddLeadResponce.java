package Mahindra_Finance_JD.Response;


public class AddLeadResponce {
    private String status;
    private String data;
    private ErrorResponse errorResponse;

    public AddLeadResponce(String status, String data) {
        this.status = status;
        this.data = data;
    }

    public  AddLeadResponce(String status,ErrorResponse errorResponse){
        this.status=status;
        this.errorResponse=errorResponse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }
}
