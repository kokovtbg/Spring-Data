package softuni.exam.dto;

public class PicturesImportDTO {
    private String name;
    private String dateAndTime;
    private long car;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public long getCar() {
        return car;
    }

    public void setCar(long car) {
        this.car = car;
    }

    public boolean isValid() {
        if (this.name.length() < 3 || this.name.length() > 19) {
            return false;
        }
        return true;
    }
}
