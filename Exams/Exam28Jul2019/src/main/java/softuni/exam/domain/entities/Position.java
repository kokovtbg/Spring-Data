package softuni.exam.domain.entities;

public enum Position {
    GK("GK"), RB("RB"), LB("LB"), CM("CM"),
    RM("RM"), LM("LM"), LW("LW"), RW("RW"), ST("ST");

    private String value;

    Position(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
