package softuni.exam.models.entity;

public enum ApartmentType {
    TwoRooms("two_rooms"), ThreeRooms("three_rooms"), FourRooms("four_rooms");

    private String value;

    ApartmentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
