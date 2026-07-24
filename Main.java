import java.time.LocalDateTime;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        QuRadar radar = new QuRadar();

        // register the rules we currently have, more can be added here
        // later without touching QuRadar
        radar.addRule(new SpeedLimitRule(CarType.TRUCK, 60, 300));
        radar.addRule(new SpeedLimitRule(CarType.PRIVATE, 80, 300));
        radar.addRule(new SeatbeltRule(100));

        // car 1: clean, no violations
        VehicleObservation car1 = new VehicleObservation(
                "XYZ111", LocalDateTime.now(), CarType.PRIVATE, 70, true);

        // car 2: speeding only
        VehicleObservation car2 = new VehicleObservation(
                "DEF222", LocalDateTime.now(), CarType.TRUCK, 75, true);

        // car 3: speeding + seatbelt not fastened
        VehicleObservation car3 = new VehicleObservation(
                "ABC1234", LocalDateTime.now(), CarType.PRIVATE, 94, false);

        VehicleObservation[] cars = { car1, car2, car3 };

        for (VehicleObservation car : cars) {
            Fine fine = radar.handleObservation(car);
            if (fine == null) {
                System.out.println("Car " + car.getPlateNumber() + " -> no violations");
            } else {
                fine.print();
            }
            System.out.println();
        }

        System.out.println("All fines:");
        radar.getAllPossibleFines();
        System.out.println();

        System.out.println("Rules broken count:");
        for (Map.Entry<String, Integer> entry : radar.getRuleBreakCount().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
