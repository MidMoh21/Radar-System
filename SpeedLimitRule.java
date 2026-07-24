// checks that a given car type does not exceed its max allowed speed.
// you can add as many of these as you want (different limit per car type)
// without ever opening QuRadar.
public class SpeedLimitRule implements TrafficRule {

    private CarType carType;
    private double maxSpeed;
    private double fee;

    public SpeedLimitRule(CarType carType, double maxSpeed, double fee) {
        this.carType = carType;
        this.maxSpeed = maxSpeed;
        this.fee = fee;
    }

    @Override
    public String getRuleName() {
        return carType + " speed limit";
    }

    @Override
    public Violation checkViolation(VehicleObservation observation) {
        if (observation.getCarType() != carType) {
            return null;
        }

        if (observation.getSpeed() > maxSpeed) {
            String desc = "speed of " + (int) observation.getSpeed()
                    + " exceeded max allowed " + (int) maxSpeed;
            return new Violation(desc, fee);
        }

        return null;
    }
}
