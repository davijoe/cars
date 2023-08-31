package dat3.car.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.entity.Car;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarResponse {

        int id;
        String brand;
        String model;
        double pricePrDay;

        public CarResponse(Car c, boolean includeAll){
            this.brand = c.getBrand();
            this.model = c.getModel();
            this.pricePrDay = c.getPricePrDay();
        }
}
