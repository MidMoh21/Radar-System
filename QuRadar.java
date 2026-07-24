import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * QuRadar
 * -------
 * This is the core of the traffic radar system. It does NOT talk to the
 * physical radar hardware, it just receives the reading that the radar
 * already captured (plate number, date, car type, speed, seatbelt status)
 * through VehicleObservation and decides what to do with it.
 *
 * The decision part is rule based, not a trained AI model - every check is
 * a plain if/condition living inside a class that implements TrafficRule
 * (SpeedLimitRule, SeatbeltRule, ...). QuRadar itself does not know the
 * details of any rule, it just loops over whatever rules were registered
 * and asks each one "did this car break you or not?". That is basically
 * a very small expert system: a fixed set of rules evaluated against the
 * observation, instead of a neural network/ML model making a probability
 * based prediction. So there is no external AI model plugged in here -
 * if the task requires one later (e.g. a model that predicts speed from
 * the camera frames, or classifies the car type from an image), it would
 * sit in front of QuRadar and just fill a VehicleObservation object, the
 * rest of the flow below does not change.
 *
 * Because everything works through the TrafficRule interface, adding a
 * new rule later (say, a rule for buses, or a rule based on time of day)
 * only means creating a new class that implements TrafficRule and adding
 * it with addRule(). Nothing inside this class needs to change.
 */
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
