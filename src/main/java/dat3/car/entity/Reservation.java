package dat3.car.entity;


import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Reservation extends AdminDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    private LocalDate rentalDate;
    private LocalDate reservationDate;

    public Reservation(LocalDate reservationDate, Car car, Member member) {
        this.reservationDate = reservationDate;
        this.car = car;
        this.member = member;
        member.addReservation(this);
        car.addReservation(this);
    }
}
    //private LocalDate returnDate; ?