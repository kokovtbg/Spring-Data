package softuni.exam.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sellers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SellersRootImportDTO {
    @XmlElement(name = "seller")
    private List<SellerImportDTO> sellers;

    public SellersRootImportDTO() {
    }

    public List<SellerImportDTO> getSellers() {
        return sellers;
    }

    public void setSellers(List<SellerImportDTO> sellers) {
        this.sellers = sellers;
    }
}
