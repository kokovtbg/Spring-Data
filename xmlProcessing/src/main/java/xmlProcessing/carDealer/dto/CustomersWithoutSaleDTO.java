package xmlProcessing.carDealer.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomersWithoutSaleDTO {
    @XmlElement(name = "customer")
    private List<CustomerWithoutSaleDTO> customers;

    public CustomersWithoutSaleDTO() {
    }

    public List<CustomerWithoutSaleDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerWithoutSaleDTO> customers) {
        this.customers = customers;
    }
}
