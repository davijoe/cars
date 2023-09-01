package dat3.car.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.entity.Car;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarResponse {

    Integer id;
    String brand;
    String model;
    Double pricePrDay;
    Integer bestDiscount;
    LocalDateTime created;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    LocalDateTime edited;


    //Convert Car Entity to Car DTO
    public CarResponse(Car c, boolean includeAll){
        this.id = c.getId();
        this.brand = c.getBrand();
        this.model = c.getModel();
        this.pricePrDay = c.getPricePrDay();
        if(includeAll){
            this.created = c.getCreated();
            this.edited = c.getEdited();
            this.bestDiscount = c.getBestDiscount();
        }
    }
}
