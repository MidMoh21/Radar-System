import java.util.List;

public class Fine {

    private String plateNumber;
    private List<Violation> violations;

    public Fine(String plateNumber, List<Violation> violations) {
        this.plateNumber = plateNumber;
        this.violations = violations;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public List<Violation> getViolations() {
        return violations;
    }

    public double getTotalAmount() {
        double total = 0;
        for (Violation v : violations) {
            total += v.getFee();
        }
        return total;
    }

    public void print() {
        System.out.println("Traffic for car " + plateNumber);
        System.out.println("Total amount: " + (int) getTotalAmount() + " EGP");
        System.out.println("Violations:");
        for (Violation v : violations) {
            System.out.println("- " + v.getDescription() + " : " + (int) v.getFee() + " EGP");
        }
    }
}
