package xmlProcessing.carDealer.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SuppliersNotImporterDTO {
    @XmlElement(name = "supplier")
    private List<SupplierNotImporterDTO> suppliers;

    public SuppliersNotImporterDTO() {
    }

    public List<SupplierNotImporterDTO> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierNotImporterDTO> suppliers) {
        this.suppliers = suppliers;
    }
}
