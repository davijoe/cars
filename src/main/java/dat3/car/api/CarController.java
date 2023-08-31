package dat3.car.api;

import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import dat3.car.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
class CarController {

    CarService carService;

    public CarController(CarService carService){
        this.carService = carService;
    }

    //Security ???
    @GetMapping
    List<CarResponse> getCars(){
        return carService.getCars(false);
    }

    //Security ???
    @GetMapping(path = "/{id}")
    CarResponse getCarById(@PathVariable int id) throws Exception {
        return carService.findById(id);
    }

    //Security --> Anonymous
    CarResponse addCar(@RequestBody CarRequest body){
        return carService.addCar(body);
    }

    //Security --> ADMIN

    //Security --> ADMIN

}
