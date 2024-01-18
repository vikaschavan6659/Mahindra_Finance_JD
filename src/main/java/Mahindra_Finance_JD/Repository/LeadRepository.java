package Mahindra_Finance_JD.Repository;

import Mahindra_Finance_JD.Model.LeadData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeadRepository extends JpaRepository<LeadData ,Integer> {

    List<LeadData> findByMobileNumber(String mobileNumber);
}
