package Mahindra_Finance_JD.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "LeadData")
public class LeadData {


    @Id
    @Column(name = "leadId")
    private int leadId;

    @Column(name = "First_Name")
    private String firstName;

    @Column(name ="Middle_Name")
    private String middleName;

    @Column(name ="Last_Name")
    private String lastName;

    @Column(name ="Mobile_Number")
    private String mobileNumber;

    @Column(name ="Gender")
    private String gender;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name ="DOB")
    private LocalDate dob;

    @Column(name ="Email")
    private String email;

    public LeadData(int leadId, String firstName, String middleName, String lastName, String mobileNumber, String gender, LocalDate DOB, String email) {
        this.leadId = leadId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.gender = gender;
        this.dob = DOB;
        this.email = email;
    }

    public LeadData() {
    }

    public int getLeadId() {
        return leadId;
    }

    public void setLeadId(int leadId) {
        this.leadId = leadId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
