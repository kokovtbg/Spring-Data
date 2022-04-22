package xmlProcessing.carDealer.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class SalesCarCustomerDTO {
    @XmlElement(name = "sale")
    private List<SaleCarCustomerDTO> sales;

    public SalesCarCustomerDTO() {
    }

    public List<SaleCarCustomerDTO> getSales() {
        return sales;
    }

    public void setSales(List<SaleCarCustomerDTO> sales) {
        this.sales = sales;
    }
}
