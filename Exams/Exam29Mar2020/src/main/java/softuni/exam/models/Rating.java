package softuni.exam.models;

public enum Rating {
    GOOD("GOOD"), BAD("BAD"), UNKNOWN("UNKNOWN");

    private String value;

    Rating(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
