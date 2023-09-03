package dat3.car.service;


import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CarService {

    CarRepository carRepository;

    public CarService(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    public List<CarResponse> getCars(boolean includeAll){
        List<Car> cars = carRepository.findAll();
        List<CarResponse> response = cars.stream().map(car ->
                new CarResponse(car, includeAll)).toList();
        return cars.stream().map(car -> new CarResponse(car, includeAll)).toList();
    }

    public CarResponse findById(int id){
        Car car = getCarById(id);
        return new CarResponse(car, true);
    }

    public CarResponse addCar(CarRequest body){
        if(carRepository.existsByBrandAndModel(body.getBrand(), body.getModel())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "That car already exist");
        }
        Car newCar = CarRequest.getCarEntity(body);
        carRepository.save(newCar);
        return new CarResponse(newCar, true);
    }


    public ResponseEntity<Boolean> editCar(CarRequest body, Integer id){
        Car car = getCarById(id);

        //Check if id is the same. If not, throw exception
        if(!id.equals(body.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"ID Cannot be changed");
        }

        car.setBrand(body.getBrand());
        car.setModel(body.getModel());
        car.setPricePrDay(body.getPricePrDay());
        car.setBestDiscount(body.getBestDiscount());
        carRepository.save(car);

        return ResponseEntity.ok(true);
    }


//    public void editCar(CarRequest body, int id){
//        Car car = getCarById(id);
//        if(body.getId() == id){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cannot change id");
//        }
//        car.setBrand(body.getBrand());
//        car.setModel(body.getModel());
//        car.setPricePrDay(body.getPricePrDay());
//        car.setBestDiscount(body.getBestDiscount());
//        carRepository.save(car);
//    }

    public void setBestDiscountForCar(int id, int value){
        Car car = getCarById(id);
        car.setBestDiscount(value);
        carRepository.save(car);
    }

    Car getCarById(int id) {
        return carRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Car not found"));
    }

    public void deleteCarById(int id){
        Car car = getCarById(id);
        System.out.println(car.getId()+": "+car.getBrand());
        carRepository.delete(car);
        System.out.println("Car deleted");
        System.out.println(car.getId()+": "+car.getBrand());
    }
}
