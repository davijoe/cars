package dat3.car.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "car_brand", length = 50, nullable = false)
    String brand;

    @Column(name = "car_model", length = 60, nullable = false)
    String model;

    @Column(name = "rental_price_day")
    double pricePrDay;

    public Car(String brand, String model, double pricePrDay){
        this.brand = brand;
        this.model = model;
        this.pricePrDay = pricePrDay;
    }

}
