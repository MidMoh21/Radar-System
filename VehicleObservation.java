import java.time.LocalDateTime;

// simple data holder that represents one car passing in front of the radar
public class VehicleObservation {

    private String plateNumber;
    private LocalDateTime date;
    private CarType carType;
    private double speed;
    private boolean seatbeltFastened;

    public VehicleObservation(String plateNumber, LocalDateTime date, CarType carType,
                               double speed, boolean seatbeltFastened) {
        this.plateNumber = plateNumber;
        this.date = date;
        this.carType = carType;
        this.speed = speed;
        this.seatbeltFastened = seatbeltFastened;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public CarType getCarType() {
        return carType;
    }

    public double getSpeed() {
        return speed;
    }

    public boolean isSeatbeltFastened() {
        return seatbeltFastened;
    }
}
