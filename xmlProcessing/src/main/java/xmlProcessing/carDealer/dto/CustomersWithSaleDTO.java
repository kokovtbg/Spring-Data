package xmlProcessing.carDealer.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomersWithSaleDTO {
    @XmlElement(name = "customer")
    private List<CustomerWithSaleDTO> customers;

    public CustomersWithSaleDTO() {
    }

    public List<CustomerWithSaleDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerWithSaleDTO> customers) {
        this.customers = customers;
    }
}
