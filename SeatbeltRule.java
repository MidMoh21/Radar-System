public class SeatbeltRule implements TrafficRule {

    private double fee;

    public SeatbeltRule(double fee) {
        this.fee = fee;
    }

    @Override
    public String getRuleName() {
        return "Seatbelt rule";
    }

    @Override
    public Violation checkViolation(VehicleObservation observation) {
        if (!observation.isSeatbeltFastened()) {
            return new Violation("Seatbelt not fastned", fee);
        }
        return null;
    }
}
