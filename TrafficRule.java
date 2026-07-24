// any traffic rule the radar should check against must implement this.
// this is the whole trick behind adding new rules later without touching
// QuRadar itself - the radar only knows about this interface, not the
// concrete rules.
public interface TrafficRule {

    String getRuleName();

    // return null if the observation does not break this rule
    Violation checkViolation(VehicleObservation observation);
}
