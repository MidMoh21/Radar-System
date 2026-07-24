public interface TrafficRule {

    String getRuleName();

    // return null if the observation does not break this rule
    Violation checkViolation(VehicleObservation observation);
}
