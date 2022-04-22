package xmlProcessing.carDealer.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomersRegisterDTO {
    @XmlElement(name = "customer")
    private List<CustomerRegisterDTO> customers;

    public CustomersRegisterDTO() {
    }

    public List<CustomerRegisterDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerRegisterDTO> customers) {
        this.customers = customers;
    }
}
