package dat3.car.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.entity.Reservation;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationResponse {

    int id;
    int carId;
    String brand;
    String model;
    LocalDate reservationDate;

    public ReservationResponse(Reservation r){
        this.id = r.getId();
        this.carId = r.getCar().getId();
        this.brand = r.getCar().getBrand();
        this.model = r.getCar().getModel();
        this.reservationDate = r.getRentalDate();
    }
}
