package exam.dto;

import java.math.BigDecimal;

public class LaptopExportDTO {
    private String macAddress;
    private double cpuSpeed;
    private int ram;
    private int storage;
    private BigDecimal price;
    private String shopName;
    private String shopTownName;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopTownName() {
        return shopTownName;
    }

    public void setShopTownName(String shopTownName) {
        this.shopTownName = shopTownName;
    }

    @Override
    public String toString() {
        return String.format("Laptop - %s\n" +
                "*Cpu speed - %.2f\n" +
                "**Ram - %d\n" +
                "***Storage - %d\n" +
                "****Price - %s\n" +
                "#Shop name - %s\n" +
                "##Town - %s",
                this.getMacAddress(), this.getCpuSpeed(), this.getRam(), this.getStorage(), this.getPrice(),
                this.getShopName(), this.getShopTownName());
    }
}
