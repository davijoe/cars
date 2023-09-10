package dat3.car.api;

import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cars")
public class CarController {

    CarService carService;

    public CarController(CarService carService){
        this.carService = carService;
    }

    //Security --> ADMIN
    @GetMapping()
    List<CarResponse> getCars(){
        return carService.getCars(false);
    }

    //Security ???
    @GetMapping(path = "/{id}")
    CarResponse getCarById(@PathVariable int id) throws Exception {
        return carService.findById(id);
    }

    //Security --> Anonymous
    @PostMapping()
    CarResponse addCar(@RequestBody CarRequest body){
        return carService.addCar(body);
    }

    //Security --> ADMIN
    @PutMapping("/{id}")
    ResponseEntity<Boolean> editCar(@RequestBody CarRequest body, @PathVariable int id){
        return carService.editCar(body, id);
    }

    //Security --> ADMIN
    @PatchMapping("/best-discount/{id}/{value}")
    void setBestDiscountForCar(@PathVariable int id, @PathVariable int value){
        carService.setBestDiscountForCar(id, value);
    }

    //Security --> ADMIN
    @DeleteMapping("/{id}")
    void deleteCarById(@PathVariable int id){
        carService.deleteCarById(id);
    }
}
