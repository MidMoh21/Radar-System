import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuRadar {

    private List<TrafficRule> rules;
    private List<Fine> issuedFines;
    private Map<String, Integer> ruleBreakCount;

    public QuRadar() {
        this.rules = new ArrayList<>();
        this.issuedFines = new ArrayList<>();
        this.ruleBreakCount = new HashMap<>();
    }

    public void addRule(TrafficRule rule) {
        rules.add(rule);
    }

    // feed one car reading to the radar, returns the fine if any violation
    // happened, or null if the car is clean
    public Fine handleObservation(VehicleObservation observation) {
        List<Violation> violations = new ArrayList<>();

        for (TrafficRule rule : rules) {
            Violation violation = rule.checkViolation(observation);
            if (violation != null) {
                violations.add(violation);
                ruleBreakCount.merge(rule.getRuleName(), 1, Integer::sum);
            }
        }

        if (violations.isEmpty()) {
            return null;
        }

        Fine fine = new Fine(observation.getPlateNumber(), violations);
        issuedFines.add(fine);
        return fine;
    }

    // plate number + total amount for every fine issued so far
    public void getAllPossibleFines() {
        for (Fine fine : issuedFines) {
            System.out.println(fine.getPlateNumber() + " -> " + (int) fine.getTotalAmount() + " EGP");
        }
    }

    public Map<String, Integer> getRuleBreakCount() {
        return ruleBreakCount;
    }

    public List<Fine> getIssuedFines() {
        return issuedFines;
    }
}
