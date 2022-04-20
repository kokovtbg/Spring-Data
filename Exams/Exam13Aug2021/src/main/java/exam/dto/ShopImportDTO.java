package exam.dto;

import exam.model.Town;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlRootElement(name = "shop")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopImportDTO {
    @XmlElement
    private String address;
    @XmlElement(name = "employee-count")
    private int employeeCount;
    @XmlElement
    private BigDecimal income;
    @XmlElement
    private String name;
    @XmlElement(name = "shop-area")
    private int shopArea;
    @XmlElement(name = "town")
    private TownNameXMLDTO townNameDTO;
    @XmlTransient
    private Town town;

    public ShopImportDTO() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getShopArea() {
        return shopArea;
    }

    public void setShopArea(int shopArea) {
        this.shopArea = shopArea;
    }

    public TownNameXMLDTO getTownNameDTO() {
        return townNameDTO;
    }

    public void setTownNameDTO(TownNameXMLDTO townNameDTO) {
        this.townNameDTO = townNameDTO;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public boolean isValid() {
        if (this.name.length() < 4) {
            return false;
        }
        if (this.income.doubleValue() < 20000) {
            return false;
        }
        if (this.address.length() < 4) {
            return false;
        }
        if (this.employeeCount < 1 || this.employeeCount > 50) {
            return false;
        }
        if (this.shopArea < 150) {
            return false;
        }
        return true;
    }
}
