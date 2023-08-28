package dat3.car.api;

import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
class CarController {

    @Autowired
    CarRepository carRepository;

    @GetMapping("/bad")
    public List<Car> getCarsBad(){
        return carRepository.findAll();
    }
}
