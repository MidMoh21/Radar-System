// represents a single violation caught by one rule, with its own fee
public class Violation {

    private String description;
    private double fee;

    public Violation(String description, double fee) {
        this.description = description;
        this.fee = fee;
    }

    public String getDescription() {
        return description;
    }

    public double getFee() {
        return fee;
    }
}
