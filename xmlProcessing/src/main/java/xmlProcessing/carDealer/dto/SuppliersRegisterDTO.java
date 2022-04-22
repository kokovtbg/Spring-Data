package xmlProcessing.carDealer.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SuppliersRegisterDTO {
    @XmlElement(name = "supplier")
    private List<SupplierRegisterDTO> suppliers;

    public SuppliersRegisterDTO() {
    }

    public List<SupplierRegisterDTO> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierRegisterDTO> suppliers) {
        this.suppliers = suppliers;
    }
}
