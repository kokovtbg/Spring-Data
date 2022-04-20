package exam.dto;

import com.google.gson.annotations.SerializedName;
import exam.model.Shop;
import exam.model.WarrantyType;

import java.math.BigDecimal;

public class LaptopsImportDTO {
    private String macAddress;
    private double cpuSpeed;
    private int ram;
    private int storage;
    private String description;
    private BigDecimal price;
    private String warrantyType;
    @SerializedName(value = "shop")
    private ShopNameDTO shopNameDTO;
    private Shop shopInRepo;

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public double getCpuSpeed() {
        return cpuSpeed;
    }

    public void setCpuSpeed(double cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getWarrantyType() {
        return warrantyType;
    }

    public void setWarrantyType(String warrantyType) {
        this.warrantyType = warrantyType;
    }

    public ShopNameDTO getShopNameDTO() {
        return shopNameDTO;
    }

    public void setShopNameDTO(ShopNameDTO shopNameDTO) {
        this.shopNameDTO = shopNameDTO;
    }

    public Shop getShopInRepo() {
        return shopInRepo;
    }

    public void setShopInRepo(Shop shopInRepo) {
        this.shopInRepo = shopInRepo;
    }

    public boolean isValid() {
        if (this.macAddress.length() < 9) {
            return false;
        }
        if (this.cpuSpeed <= 0) {
            return false;
        }
        if (this.ram < 8 || this.ram > 128) {
            return false;
        }
        if (this.storage < 128 || this.storage > 1024) {
            return false;
        }
        if (this.description.length() < 10) {
            return false;
        }
        if (this.price.doubleValue() <= 0) {
            return false;
        }
        if (!this.warrantyType.equals("BASIC") && !this.warrantyType.equals("PREMIUM")
                && !this.warrantyType.equals("LIFETIME")) {
            return false;
        }
        return true;
    }
}
